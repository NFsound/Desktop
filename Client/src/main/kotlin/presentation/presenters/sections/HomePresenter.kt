package presentation.presenters.sections

import application.SonusApplication
import interactors.HomeInteractor
import models.core.music.Playlist
import models.core.music.Track
import models.utils.PlaylistImage
import presentation.presenters.main.CenterPresenter
import presentation.sections.home.HomeView
import javax.inject.Inject

class HomePresenter:SectionPresenter {
    @Inject
    lateinit var viewState: HomeView

    @Inject
    lateinit var homeInteractor: HomeInteractor

    init {
        SonusApplication.getInstance().applicationComponent.inject(this)
    }

    override lateinit var centerPresenter: CenterPresenter

    override fun filter(text: String) {

    }

    val testList = listOf(
        Playlist(
            arrayListOf(
                Track.emptyTrack()
            ),
            "Some name of list",
            PlaylistImage()
        ),
        Playlist(
            arrayListOf(
                Track.emptyTrack()
            ),
            "Some name of list",
            PlaylistImage()
        )

    )

    override fun onInitialLoad() {
        viewState.renderPopularPlaylists(testList)
        viewState.renderAccountPlaylists(testList)
    }

    fun onPlayPlaylistClicked(playlist: Playlist){
        centerPresenter.onPlayPlaylistClicked(playlist)
    }

    fun onPausePlaylistClicked(playlist: Playlist){
        centerPresenter.onPausePlaylistClicked(playlist)
    }
}