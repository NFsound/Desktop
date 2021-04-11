package presentation.presenters.sections

import application.SonusApplication
import interactors.HomeInteractor
import models.core.Playlist
import models.core.Track
import models.utils.PlaylistImage
import presentation.sections.account.AccountView
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
        ),
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
        ),
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
    }

}