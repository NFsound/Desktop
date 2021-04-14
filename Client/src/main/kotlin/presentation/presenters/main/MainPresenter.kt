package presentation.presenters.main

import application.SonusApplication
import interactors.HomeInteractor
import javafx.application.Platform
import javafx.scene.media.Media
import models.core.music.Playlist
import models.core.music.Track
import presentation.main_views.sides.BottomMenuView
import presentation.main_views.sides.LeftMenuView
import presentation.main_views.sides.TopMenuView
import presentation.main_views.center.CenterView
import presentation.menu.item.MenuItem
import javax.inject.Inject
import javafx.scene.media.MediaPlayer
import models.utils.PlaylistImage
import tornadofx.toProperty
import java.nio.file.Paths

class MainPresenter() : CenterPresenter {


    @Inject
    lateinit var centerViewState: CenterView

    @Inject
    lateinit var leftViewState: LeftMenuView

    @Inject
    lateinit var topViewState: TopMenuView

    @Inject
    lateinit var bottomViewState: BottomMenuView

    @Inject
    lateinit var homeInteractor: HomeInteractor

    //current state of player
    lateinit var player: MediaPlayer
    lateinit var currentPlaylist: Playlist
    lateinit var currentTrack: Track

    init {
        SonusApplication.getInstance().applicationComponent.inject(this)
        setUpPlayer()
    }

    fun setUpPlayer() {
        val path = Paths.get(SonusApplication.resourcePath + "music/song.mp3").toUri().toString()
        player = MediaPlayer(Media(path))
        player.setOnEndOfMedia {
            player = if (isRandom) {
                MediaPlayer(Media(currentPlaylist.nextRandomTrack().resPath))
            } else {
                MediaPlayer(Media(currentPlaylist.nextTrack().resPath))
            }
        }
    }


    override fun onSectionSelected(menuItem: MenuItem, index: Int) {
        centerViewState.setSection(menuItem)
        leftViewState.setSection(menuItem, index)
    }

    override fun onSearch(text: String) {
        centerViewState.filter(text)
    }

    override fun onPreviousClicked() {
        currentTrack = if (isRandom) {
            currentPlaylist.nextRandomTrack()
        } else {
            currentPlaylist.previousTrack()
        }
        player = MediaPlayer(Media(currentTrack.resPath))
    }

    override fun onPlayClicked() {
        player.play()
        var time = (player.currentTime.toSeconds() / player.totalDuration.toSeconds()).toProperty()
        bottomViewState.slider.valueProperty().bind(time)
    }

    override fun onPauseClicked() {
        player.pause()
    }

    override fun onPlusClicked() {
        homeInteractor.getMyPlaylists()
            .onErrorResumeWith {
                Platform.runLater {
                    bottomViewState.openPlaylistView(emptyList(), currentTrack)
                }
            }
            .subscribe { lists ->
                Platform.runLater {
                    bottomViewState.openPlaylistView(lists, currentTrack)
                }
            }
    }

    override fun onNextClicked() {
        currentTrack = if (isRandom) {
            currentPlaylist.nextRandomTrack()
        } else {
            currentPlaylist.nextTrack()
        }
        player = MediaPlayer(Media(currentTrack.resPath))
    }

    override fun onCycleClicked() {
        if (isCycled) {
            isCycled = false
        } else {
            isCycled = true
            //TODO some code
        }
    }

    override fun onShuffleClicked() {
        if (isRandom) {
            isRandom = false
        } else {
            isRandom = true
            //TODO some code
        }
    }

    override fun addToPlaylist(track: Track, playlist: Playlist) {
        playlist.addTrack(track)
    }

    override fun removeFromPlaylist(track: Track, playlist: Playlist) {
        playlist.removeTrack(track)
    }

    override fun createPlaylist(name: String, playlistImage: PlaylistImage) {
        homeInteractor.createPlaylist(Playlist(ArrayList(),name,playlistImage))
    }


    override var isCycled: Boolean = false

    override var isRandom: Boolean = false


    override fun onCurrentPlaylistClicked() {
        homeInteractor.getMyPlaylists()
            .onErrorResumeWith {  }
            .subscribe {
                lists->
            bottomViewState.showPlaylistView(currentPlaylist,lists)
        }
    }

    override fun onPlayPlaylistClicked(playlist: Playlist) {
        if (currentPlaylist != playlist) {
            currentPlaylist = playlist
            this.onNextClicked()
        } else {
            this.onPlayClicked()
        }
        bottomViewState.onPauseClick()
    }

    override fun onPausePlaylistClicked(playlist: Playlist) {
        this.onPauseClicked()
        bottomViewState.onPauseClick()
    }

}