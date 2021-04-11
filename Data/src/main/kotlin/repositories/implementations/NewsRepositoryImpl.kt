package repositories.implementations

import io.reactivex.rxjava3.core.Single
import models.core.News
import network.api.ApiService
import repositories.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val api: ApiService): NewsRepository {
    override fun getNews(): Single<List<News>> {
        return api.getAllNews()
    }
}