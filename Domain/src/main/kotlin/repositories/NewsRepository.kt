package repositories

import io.reactivex.rxjava3.core.Single
import models.core.news.News


interface NewsRepository {

    fun getNews(): Single<List<News>>

    fun filterNews(text:String): Single<List<News>>
}