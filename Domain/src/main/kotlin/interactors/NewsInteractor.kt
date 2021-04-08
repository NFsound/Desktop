package interactors

import models.core.News

interface NewsInteractor {

    fun getNews(): List<News>

    fun filterNews(): List<News>



}