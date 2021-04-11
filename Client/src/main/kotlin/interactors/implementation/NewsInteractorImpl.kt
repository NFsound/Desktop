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

    val testList = listOf(
        News(
            "TITLE", "SOME TEXT mcmdl" +
                    "ds,v;'ls,vl; slf v,slmvldvcamvgkzd;nlbjkvhnwdbjk" +
                    "sdv,.sd;lv,;lsf,vs;fl vslf ;v,rsgvkx;masel,zsd;vl mzdklvmzdlm vkzxvmdlm" +
                    "sdpv,z;vmpsfmvkfsnmbvsekmbva;'c'Lc;" +
                    "ADMcvzdmvdlzvxf"
        )
    )
    private lateinit var allNews: List<News>

    override fun getAllNews(): Single<List<News>> {
        return newsRepository.getNews()
            .doOnSuccess{
                    list-> allNews = list
            }.doOnError{
                allNews = testList
            }
    }

    override fun filterNews(text: String): List<News> {
        return allNews.filter {
            it.title.toLowerCase().contains(text.toLowerCase()) ||
                    it.text.toLowerCase().contains(text.toLowerCase())
        }
    }
}