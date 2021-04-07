package repositories

import io.reactivex.rxjava3.core.Single
import models.wrappers.NewsList

interface NewsRepository {

    fun getNews(): Single<NewsList>


}