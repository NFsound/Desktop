package repositories

import io.reactivex.rxjava3.core.Single
import models.core.News
import models.wrappers.NewsList

interface NewsRepository {

    fun getNews(): Single<List<News>>

}