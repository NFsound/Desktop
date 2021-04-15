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

    private var currentProgress: PublishSubject<Double> = PublishSubject.create()
    private var currentVolume: PublishSubject<Double> = PublishSubject.create()
    init {
        SonusApplication.getInstance().applicationComponent.inject(this)
        setUpPlayer()
        subscribeSliders()
    }

    fun subscribeSliders(){
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

        //if (this::currentTrack.isInitialized) {

            val resPath: String = SonusApplication.resourcePath + "music/song1.wav"
            //val resPath: String = currentTrack.resPath
            currentTrack = Track(0,"some name",1,
                resPath,"good author")
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
      //  }
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
        setUpPlayer()
    }

    override fun onPlayClicked() {
        player.play()
    }

    override fun onPauseClicked() {
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
        setUpPlayer()
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
        homeInteractor.createPlaylist(Playlist(1,ArrayList(),name,playlistImage))
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