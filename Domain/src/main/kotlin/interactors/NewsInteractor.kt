package interactors

import io.reactivex.rxjava3.core.Single
import models.core.News

interface NewsInteractor {

    fun getAllNews(): Single<List<News>>

    fun filterNews(text:String): List<News>

}