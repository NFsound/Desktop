package repositories.implementations

import io.reactivex.rxjava3.core.Single
import models.core.news.News
import network.api.ApiService
import repositories.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: ApiService
) : NewsRepository {

    private var allNews: List<News> = emptyList()

    override fun getNews(): Single<List<News>> {
        return api.getAllNews()
            .map { it.list }
            .doAfterSuccess {
                allNews = it
            }
    }

    override fun filterNews(text: String): Single<List<News>> {
        return Single.just(allNews.filter {
            it.title.toLowerCase().contains(text.toLowerCase()) ||
                    it.text.toLowerCase().contains(text.toLowerCase())
        })
    }
}