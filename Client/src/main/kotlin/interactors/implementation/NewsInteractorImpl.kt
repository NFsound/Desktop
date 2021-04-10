package interactors.implementation

import interactors.NewsInteractor
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import models.core.News
import repositories.NewsRepository
import javax.inject.Inject

class NewsInteractorImpl @Inject constructor(
    private val newsRepository: NewsRepository
) : NewsInteractor {

    private lateinit var allNews: List<News>

    override fun getAllNews(): Single<List<News>> {
        return newsRepository.getNews()
            .doOnSuccess{
                    list-> allNews = list
            }
    }

    override fun filterNews(text: String): List<News> {
        return allNews.filter {
            it.title.contains(text) || it.text.contains(text)
        }
    }
}