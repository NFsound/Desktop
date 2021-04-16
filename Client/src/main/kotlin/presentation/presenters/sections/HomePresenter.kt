package presentation.presenters.sections

import application.SonusApplication
import interactors.HomeInteractor
import javafx.application.Platform
import models.core.music.Playlist
import models.core.music.Track
import models.utils.PlaylistImage
import presentation.presenters.main.CenterPresenter
import presentation.sections.home.HomeView
import javax.inject.Inject

class HomePresenter : SectionPresenter {

    @Inject
    lateinit var viewState: HomeView

    @Inject
    lateinit var homeInteractor: HomeInteractor

    init {
        SonusApplication.getInstance().applicationComponent.inject(this)
    }

    override lateinit var centerPresenter: CenterPresenter

    override fun filter(text: String) {
        homeInteractor.filterPlaylists(text)
            .onErrorResumeWith {  }
            .subscribe {
                lists->

            }
    }


    override fun onInitialLoad() {
        homeInteractor.getMyPlaylists().subscribe { list ->
            Platform.runLater {
                viewState.renderAccountPlaylists(list)
            }
        }
        homeInteractor.getPopularPlaylists().subscribe { list ->
            Platform.runLater {
                viewState.renderPopularPlaylists(list)
            }
        }
    }

}