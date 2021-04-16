package presentation.presenters.main

import models.core.music.Playlist
import models.core.music.Track
import models.utils.PlaylistImage
import presentation.menu.item.MenuItem
import tornadofx.SVGIcon

interface CenterPresenter {

    fun onSectionSelected(menuItem: MenuItem, index: Int)

    fun onSearch(text: String)

    fun onPreviousClicked()

    fun onPlayClicked()

    fun onPauseClicked()

    fun onPlusClicked()

    fun onNextClicked()

    fun onCycleClicked()

    fun onShuffleClicked()

    fun addToPlaylist(track: Track, playlist: Playlist)

    fun removeFromPlaylist(track: Track, playlist: Playlist)

    fun createPlaylist(name: String, playlistImage: PlaylistImage)

    var isCycled: Boolean

    var isRandom: Boolean

    fun onCurrentPlaylistClicked()

    fun onPlayPlaylistClicked(playlist: Playlist, playIcon:SVGIcon, pauseIcon:SVGIcon)

    fun onPlayTrackClicked(playIcon:SVGIcon,pauseIcon:SVGIcon,track: Track)

    fun onPauseTrackClicked()
}