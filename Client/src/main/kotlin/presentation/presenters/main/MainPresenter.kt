package presentation.presenters.main

import application.SonusApplication
import interactors.HomeInteractor
import interactors.MusicInteractor
import io.reactivex.rxjava3.subjects.PublishSubject
import javafx.application.Platform
import javafx.geometry.Insets
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.CornerRadii
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
import presentation.sections.common.Common
import presentation.styles.Colors
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
    lateinit var musicInteractor: MusicInteractor

    @Inject
    lateinit var homeInteractor: HomeInteractor

    //current state of player
    private lateinit var player: MediaPlayer
    private lateinit var currentPlaylist: Playlist
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
            if (this::player.isInitialized) {
                player.volume = it
            }
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
                player = if (isRandom) {
                    MediaPlayer(Media(currentPlaylist.nextRandomTrack().resPath))
                } else {
                    MediaPlayer(Media(currentPlaylist.nextTrack().resPath))
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


    private fun playClickManageIcons() {
        if (this::currentPauseIcon.isInitialized) {
            currentPauseIcon.isVisible = true
            currentPauseIcon.toFront()
        }
        if (this::currentPlayIcon.isInitialized) {
            currentPlayIcon.isVisible = false
            currentPlayIcon.toBack()
        }
        bottomViewState.playIcon.isVisible = false
        bottomViewState.pauseIcon.isVisible = true
        bottomViewState.pauseIcon.parent.toFront()
        bottomViewState.pauseIcon.toFront()
    }

    private fun pauseClickManageIcons() {
        if (this::currentPauseIcon.isInitialized) {
            currentPauseIcon.isVisible = false
            currentPauseIcon.toBack()
        }
        if (this::currentPlayIcon.isInitialized) {
            currentPlayIcon.isVisible = true
            currentPlayIcon.toFront()
        }
        bottomViewState.pauseIcon.isVisible = false
        bottomViewState.playIcon.isVisible = true
        bottomViewState.playIcon.parent.toFront()
        bottomViewState.playIcon.toFront()
    }

    override fun onPlusClicked() {
        if (this::currentTrack.isInitialized) {
            musicInteractor.getAllPlaylistsByAccount()
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
    }


    override fun onCycleClicked() {
        if (isCycled) {
            isCycled = false
            Common.setMouseEnterBackground(bottomViewState.repeatIcon)
            Common.setMouseLeaveBackground(bottomViewState.repeatIcon)
        } else {
            isCycled = true
            bottomViewState.repeatIcon.background = Background(
                BackgroundFill(
                    Colors.alternativeColor,
                    CornerRadii.EMPTY, Insets.EMPTY
                )
            )
            bottomViewState.repeatIcon.setOnMouseExited { }
            bottomViewState.repeatIcon.setOnMouseEntered { }
        }
    }

    override fun onShuffleClicked() {
        if (isRandom) {
            isRandom = false
            Common.setMouseEnterBackground(bottomViewState.shuffleIcon)
            Common.setMouseLeaveBackground(bottomViewState.shuffleIcon)
        } else {
            isRandom = true
            bottomViewState.shuffleIcon.background = Background(
                BackgroundFill(
                    Colors.alternativeColor,
                    CornerRadii.EMPTY, Insets.EMPTY
                )
            )
            bottomViewState.shuffleIcon.setOnMouseExited { }
            bottomViewState.shuffleIcon.setOnMouseEntered { }
        }
    }


    override fun createPlaylist(name: String, playlistImage: PlaylistImage) {
        homeInteractor.createPlaylist(Playlist(ArrayList(), name, playlistImage))
            //TODO ON ERROR
            .subscribe()
    }


    override var isCycled: Boolean = false

    override var isRandom: Boolean = false


    override fun onCurrentPlaylistClicked() {
        if (this::player.isInitialized && currentPlaylist != Playlist.emptyPlaylist) {
            Platform.runLater {
                homeInteractor.getMyPlaylists()
                    .onErrorResumeWith { } //TODO ON ERROR
                    .subscribe { lists ->
                        Platform.runLater {
                            bottomViewState.showPlaylistView(currentPlaylist, lists)
                        }
                    }

            }
        }
    }

    override fun onNextClicked() {
        if (this::player.isInitialized && currentPlaylist != Playlist.emptyPlaylist) {
            currentTrack = if (isRandom) {
                currentPlaylist.nextRandomTrack()
            } else {
                currentPlaylist.nextTrack()
            }
            if (this::player.isInitialized) {
                player.stop()
            }
            setUpPlayer()
            player.play()
        }
    }

    override fun onPlayClicked() {
        playClickManageIcons()
        if (this::player.isInitialized) {
            player.play()
        }
    }

    override fun onPreviousClicked() {
        if (this::player.isInitialized && currentPlaylist != Playlist.emptyPlaylist) {
            currentTrack = if (isRandom) {
                currentPlaylist.nextRandomTrack()
            } else {
                currentPlaylist.previousTrack()
            }
            if (this::player.isInitialized) {
                player.stop()
            }
            setUpPlayer()
            player.play()
        }
    }

    override fun onPauseClicked() {
        pauseClickManageIcons()
        if (this::player.isInitialized) {
            player.pause()
        }
    }

    override fun onPlayPlaylistClicked(
        playlist: Playlist,
        playIcon: SVGIcon,
        pauseIcon: SVGIcon
    ) {
        pauseClickManageIcons()
        currentPlayIcon = playIcon
        currentPauseIcon = pauseIcon
        if (currentPlaylist != playlist) {
            currentPlaylist = playlist
            currentTrack = playlist.nextTrack()
            setUpPlayer()
            playClickManageIcons()
        } else {
            playClickManageIcons()
        }

        player.play()
    }

    override fun onPlayTrackClicked(playIcon: SVGIcon, pauseIcon: SVGIcon, track: Track) {
        currentPlaylist = Playlist.emptyPlaylist
        if (this::player.isInitialized) {
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
        currentPlayIcon = playIcon
        currentPauseIcon = pauseIcon
        if (player.status != MediaPlayer.Status.PLAYING) {
            player.play()
            playClickManageIcons()
        } else {
            player.pause()
            pauseClickManageIcons()
        }
    }


    override fun addToPlaylist(track: Track, playlist: Playlist) {
        playlist.addTrack(track)
    }

    override fun removeFromPlaylist(track: Track, playlist: Playlist) {
        playlist.removeTrack(track)
    }


}