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
            .onErrorResumeWith {
                viewState.showErrorMessage("Nothing found")
            }
            .subscribe { lists ->

            }
    }


    override fun onInitialLoad() {
        Platform.runLater {
            homeInteractor.getMyPlaylists()
                .doOnError {
                    it
                }
                .onErrorResumeWith {
                    Platform.runLater {
                        viewState.showErrorMessage("Couldn't load your playlists")
                    }
                }
                .subscribe { list ->
                    Platform.runLater {
                        viewState.renderAccountPlaylists(list)
                    }
                }
            homeInteractor.getPopularPlaylists()
                .doOnError {
                    it
                }
                .onErrorResumeWith {
                    Platform.runLater {
                        viewState.showErrorMessage("Couldn't load popular playlists")
                    }
                }
                .subscribe {
                        list ->
                    Platform.runLater {
                        viewState.renderPopularPlaylists(list)
                    }
                }
        }
    }

    fun onPlaylistCreated() {
        onInitialLoad()
    }

}