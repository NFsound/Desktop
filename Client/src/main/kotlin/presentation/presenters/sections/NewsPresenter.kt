package presentation.presenters.sections

import application.SonusApplication
import interactors.NewsInteractor
import javafx.application.Platform
import models.core.news.News
import presentation.presenters.main.CenterPresenter
import presentation.sections.news.NewsView
import java.lang.Exception
import javax.inject.Inject

class NewsPresenter() : SectionPresenter {

    @Inject
    lateinit var viewState: NewsView

    @Inject
    lateinit var newsInteractor: NewsInteractor

    override lateinit var centerPresenter: CenterPresenter


    init {
        SonusApplication.getInstance().applicationComponent.inject(this)
    }

    override fun filter(text: String) {
        Platform.runLater {
            if (text.isNotEmpty()) {
                val filteredNews = newsInteractor.filterNews(text)
            } else {
                newsInteractor.getAllNews()
                    .onErrorResumeWith {
                        Platform.runLater {
                            viewState.showErrorMessage("No connection with server")
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
                    viewState.showErrorMessage("No connection with server")
                }
            }.subscribe { list ->
                Platform.runLater {
                    viewState.showNews(list)
                }
            }

    }
}