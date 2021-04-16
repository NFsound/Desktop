package presentation.presenters.main

import application.SonusApplication
import interactors.HomeInteractor
import interactors.MusicInteractor
import io.reactivex.rxjava3.subjects.PublishSubject
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
import tornadofx.SVGIcon
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
    lateinit var interactor: MusicInteractor

    @Inject
    lateinit var homeInteractor: HomeInteractor

    //current state of player
    private lateinit var player: MediaPlayer
    var currentPlaylist: Playlist = Playlist.emptyPlaylist
    private lateinit var currentTrack: Track

    private lateinit var currentPlayIcon: SVGIcon
    private lateinit var currentPauseIcon: SVGIcon


    private var currentProgress: PublishSubject<Double> = PublishSubject.create()
    private var currentVolume: PublishSubject<Double> = PublishSubject.create()

    init {
        SonusApplication.getInstance().applicationComponent.inject(this)
        setUpPlayer()
        subscribeSliders()
    }

    fun subscribeSliders() {
        bottomViewState.volumeSlider.valueProperty().addListener { _ ->
            currentVolume.onNext(bottomViewState.volumeSlider.value)
        }
        currentProgress.subscribe {
            bottomViewState.slider.value = it
        }
        currentVolume.subscribe {
            player.volume = it
        }
    }

    fun setUpPlayer() {

        if (this::currentTrack.isInitialized) {
            val path = Paths.get(currentTrack.resPath).toUri().toString()
            player = MediaPlayer(Media(path))
            player.setOnReady {
                bottomViewState.setTrackInfo(currentTrack, player)
            }
            player.currentTimeProperty().addListener { _ ->
                currentProgress.onNext(
                    player.currentTime.toSeconds() /
                            player.totalDuration.toSeconds()
                )
                bottomViewState.passedTimeLabel.text =
                    bottomViewState.convertTimeToMins(player.currentTime.toSeconds())
            }
            player.setOnEndOfMedia {
                try {
                    player = if (isRandom) {
                        MediaPlayer(Media(currentPlaylist.nextRandomTrack().resPath))
                    } else {
                        MediaPlayer(Media(currentPlaylist.nextTrack().resPath))
                    }
                } catch (e: Exception) {

                }
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
        if(this::player.isInitialized) {
            player.stop()
        }
        setUpPlayer()
        player.play()
    }


    override fun onPlayClicked() {
        if (this::currentPauseIcon.isInitialized) {
            currentPauseIcon.isVisible = true
        }
        if (this::currentPlayIcon.isInitialized) {
            currentPlayIcon.isVisible = false
        }
        player.play()
    }

    override fun onPauseClicked() {
        if (this::currentPauseIcon.isInitialized) {
            currentPauseIcon.isVisible = false
        }
        if (this::currentPlayIcon.isInitialized) {
            currentPlayIcon.isVisible = true
        }
        player.pause()
    }

    override fun onPlusClicked() {
        interactor.getAllPlaylistsByAccount()
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
        if(this::player.isInitialized) {
            player.stop()
        }
        setUpPlayer()
        player.play()
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
        homeInteractor.createPlaylist(Playlist(1, ArrayList(), name, playlistImage))
    }


    override var isCycled: Boolean = false

    override var isRandom: Boolean = false


    override fun onCurrentPlaylistClicked() {
        Platform.runLater {
            homeInteractor.getMyPlaylists()
                .onErrorResumeWith { }
                .subscribe { lists ->
                    Platform.runLater {
                        bottomViewState.showPlaylistView(currentPlaylist, lists)
                    }
                }
        }
    }

    override fun onPlayPlaylistClicked(
        playlist: Playlist,
        playIcon: SVGIcon,
        pauseIcon: SVGIcon
    ) {
        if (this::currentPauseIcon.isInitialized) {
            currentPauseIcon.isVisible = false
        }
        if (this::currentPlayIcon.isInitialized) {
            currentPlayIcon.isVisible = true
        }
        currentPlayIcon = playIcon
        currentPauseIcon = pauseIcon
        if (currentPlaylist != playlist) {
            currentPlaylist = playlist
            this.onNextClicked()
        } else {
            this.onPlayClicked()
        }
        player.play()
        bottomViewState.pauseIcon.isVisible = true
        bottomViewState.playIcon.isVisible = false
    }

    override fun onPlayTrackClicked(playIcon: SVGIcon, pauseIcon: SVGIcon, track: Track) {
        currentPlayIcon = playIcon
        currentPauseIcon = pauseIcon
        if (this::player.isInitialized && player != null) {
            player.pause()
            if (currentTrack != track) {
                currentTrack = track
                setUpPlayer()
            }
        } else {

            if (!this::player.isInitialized) {
                currentTrack = track
                setUpPlayer()
            }
            if (currentTrack != track) {
                currentTrack = track
                setUpPlayer()
            }
        }
        if (player.status != MediaPlayer.Status.PLAYING) {
            player.play()
            bottomViewState.pauseIcon.isVisible = true
            pauseIcon.isVisible = true
            playIcon.isVisible = false
            bottomViewState.playIcon.isVisible = false
        } else {
            player.pause()
            bottomViewState.pauseIcon.isVisible = false
            pauseIcon.isVisible = false
            playIcon.isVisible = true
            bottomViewState.playIcon.isVisible = true
        }
    }

    override fun onPauseTrackClicked() {
        TODO("Not yet implemented")
    }

}