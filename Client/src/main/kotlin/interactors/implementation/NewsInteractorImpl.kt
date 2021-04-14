package interactors.implementation

import interactors.NewsInteractor
import io.reactivex.rxjava3.core.Single
import models.core.news.News
import repositories.NewsRepository
import javax.inject.Inject

class NewsInteractorImpl @Inject constructor(
    private val newsRepository: NewsRepository
) : NewsInteractor {

    override fun getAllNews(): Single<List<News>> {
        return newsRepository.getNews()
    }

    override fun filterNews(text: String): Single<List<News>> {
        return newsRepository.filterNews(text)
    }
}