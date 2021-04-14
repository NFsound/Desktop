package presentation.presenters.main

import models.core.music.Playlist
import presentation.menu.item.MenuItem

interface CenterPresenter {

    fun onSectionSelected(menuItem: MenuItem, index: Int)

    fun onSearch(text: String)

    fun onPreviousClicked()

    fun onPlayClicked()

    fun onPauseClicked()

    fun onNextClicked()

    fun onCycleClicked()

    fun onShuffleClicked()

    var isCycled: Boolean

    var isRandom: Boolean

    fun onCurrentPlaylistClicked()

    fun onPlayPlaylistClicked(playlist: Playlist)

    fun onPausePlaylistClicked(playlist: Playlist)
}