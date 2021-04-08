package presentation.presenters.main

import application.SonusApplication
import models.core.Playlist
import models.core.Track
import presentation.main_views.sides.BottomMenuView
import presentation.main_views.sides.LeftMenuView
import presentation.main_views.sides.TopMenuView
import presentation.main_views.center.CenterView
import presentation.menu.item.MenuItem
import presentation.navigation.Navigator
import javax.inject.Inject


class MainPresenter(): CenterPresenter {

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var centerViewState: CenterView

    @Inject
    lateinit var leftViewState: LeftMenuView

    @Inject
    lateinit var topViewState: TopMenuView

    @Inject
    lateinit var bottomViewState: BottomMenuView



    init {
        SonusApplication.getInstance().applicationComponent.inject(this)
    }

    lateinit var currentPlaylist: Playlist

    lateinit var currentTrack: Track

    override fun onSectionSelected(menuItem: MenuItem, index: Int){
        navigator.navigateTo(menuItem)
        centerViewState.setSection(menuItem)
        leftViewState.setSection(menuItem, index)
    }

    override fun onSearch(text: String) {
        centerViewState.filter(text)
    }

    override fun onPreviousClicked() {
        TODO("Not yet implemented")
    }

    override fun onPlayClicked() {
        TODO("Not yet implemented")
    }

    override fun onPauseClicked() {
        TODO("Not yet implemented")
    }

    override fun onNextClicked() {
        TODO("Not yet implemented")
    }

    override fun onCycleClicked() {
        TODO("Not yet implemented")
    }

    override fun onShuffleClicked() {
        TODO("Not yet implemented")
    }


    override var isCycled: Boolean = false

    override var isRandom: Boolean = false


    override fun onCurrentPlaylistClicked() {
        TODO("Not yet implemented")
    }


}