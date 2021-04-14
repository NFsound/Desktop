package presentation.presenters.sections

import application.SonusApplication
import interactors.NewsInteractor
import javafx.application.Platform
import models.core.news.News
import presentation.presenters.main.CenterPresenter
import presentation.sections.news.NewsView
import javax.inject.Inject

class NewsPresenter() : SectionPresenter {

    @Inject
    lateinit var viewState: NewsView

    @Inject
    lateinit var newsInteractor: NewsInteractor

    override lateinit var centerPresenter: CenterPresenter

    val testList = listOf(
        News(
            "TITLE", "SOME TEXT mcmdl" +
                    "ds,v;'ls,vl; slf v,slmvldvcamvgkzd;nlbjkvhnwdbjk" +
                    "sdv,.sd;lv,;lsf,vs;fl vslf ;v,rsgvkx;masel,zsd;vl mzdklvmzdlm vkzxvmdlm" +
                    "sdpv,z;vmpsfmvkfsnmbvsekmbva;'c'Lc;" +
                    "ADMcvzdmvdlzvxf"
        ),
        News(
            "TITLE", "SOME TEXT mcmdl" +
                    "ds,v;'ls,vl; slf v,slmvldvcamvgkzd;nlbjkvhnwdbjk" +
                    "sdv,.sd;lv,;lsf,vs;fl vslf ;v,rsgvkx;masel,zsd;vl mzdklvmzdlm vkzxvmdlm" +
                    "sdpv,z;vmpsfmvkfsnmbvsekmbva;'c'Lc;" +
                    "ADMcvzdmvdlzvxf"
        ),
        News(
            "TITLE", "SOME TEXT mcmdl" +
                    "ds,v;'ls,vl; slf v,slmvldvcamvgkzd;nlbjkvhnwdbjk" +
                    "sdv,.sd;lv,;lsf,vs;fl vslf ;v,rsgvkx;masel,zsd;vl mzdklvmzdlm vkzxvmdlm" +
                    "sdpv,z;vmpsfmvkfsnmbvsekmbva;'c'Lc;" +
                    "ADMcvzdmvdlzvxf"
        ),
        News(
            "TITLE", "SOME TEXT mcmdl" +
                    "ds,v;'ls,vl; slf v,slmvldvcamvgkzd;nlbjkvhnwdbjk" +
                    "sdv,.sd;lv,;lsf,vs;fl vslf ;v,rsgvkx;masel,zsd;vl mzdklvmzdlm vkzxvmdlm" +
                    "sdpv,z;vmpsfmvkfsnmbvsekmbva;'c'Lc;" +
                    "ADMcvzdmvdlzvxfsiRGVJawiohgvoiWBEGVIZOlsiERbnowkdmbsliebdv"
        ),
        News(
            "TITLE", "SOME TEXT mcmdl" +
                    "ds,v;'ls,vl; slf v,slmvldvcamvgkzd;nlbjkvhnwdbjk" +
                    "sdv,.sd;lv,;lsf,vs;fl vslf ;v,rsgvkx;masel,zsd;vl mzdklvmzdlm vkzxvmdlm" +
                    "sdpv,z;vmpsfmvkfsnmbvsekmbva;'c'Lc;" +
                    "ADMcvzdmvdlzvxf"
        ),
        News(
            "TITLE", "SOME TEXT mcmdl" +
                    "ds,v;'ls,vl; slf v,slmvldvcamvgkzd;nlbjkvhnwdbjk" +
                    "sdv,.sd;lv,;lsf,vs;fl vslf ;v,rsgvkx;masel,zsd;vl mzdklvmzdlm vkzxvmdlm" +
                    "sdpv,z;vmpsfmvkfsnmbvsekmbva;'c'Lc;" +
                    "ADMcvzdmvdlzvxf"
        )
    )

    init {
        SonusApplication.getInstance().applicationComponent.inject(this)
    }

    override fun filter(text: String) {

        Platform.runLater {
            if (text.isNotEmpty()) {
                val filteredNews = newsInteractor.filterNews(text)
               // viewState.showNews(filteredNews)
            } else {
                newsInteractor.getAllNews()
                    .onErrorResumeWith {
                        Platform.runLater {
                            viewState.showNews(testList)
                        }
                    }
                    .subscribe { list ->
                        Platform.runLater {
                            viewState.showNews(list)
                        }
                    }
            }
        }
    }

    override fun onInitialLoad() {
        newsInteractor.getAllNews()
            .onErrorResumeWith {
                Platform.runLater {
                    viewState.showNews(testList)
                }
            }.subscribe { list ->
                Platform.runLater {
                    viewState.showNews(list)
                }
            }
    }
}