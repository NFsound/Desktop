package repositories

import io.reactivex.rxjava3.core.Single
import models.core.News


interface NewsRepository {

    fun getNews(): Single<List<News>>

}