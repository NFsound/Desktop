package presentation.presenters.sections

import application.SonusApplication
import interactors.MusicInteractor
import javafx.application.Platform
import models.core.music.Track
import models.core.networks.GenerationParams
import models.core.networks.Network
import presentation.presenters.main.CenterPresenter
import presentation.sections.music.MusicView
import tornadofx.SVGIcon
import utils.ImageProvider
import java.io.File
import java.lang.Exception
import javax.inject.Inject

class MusicPresenter : SectionPresenter {

    @Inject
    lateinit var viewState: MusicView

    @Inject
    lateinit var musicInteractor: MusicInteractor

    override lateinit var centerPresenter: CenterPresenter

    private var currentMusicFile: File? = null
    private var currentNetwork: Network? = null

    init {
        SonusApplication.getInstance().applicationComponent.inject(this)
    }

    override fun filter(text: String) {

    }

    fun onPlusIconClick(icon: SVGIcon, track: Track) {
        musicInteractor.getAllPlaylistsByAccount()
            .onErrorResumeWith {
                Platform.runLater {
                    viewState.showErrorMessage("Couldn't load your playlists")
                }
            }
            .subscribe { lists ->
                Platform.runLater {
                    viewState.openPlaylistsView(icon, lists, track)
                }
            }
    }

    fun startTrackGeneration() {
        try {
            val byteArray = currentMusicFile!!.readBytes()

            musicInteractor
                .generateTrack(byteArray, GenerationParams("fv", "fv"))
                .subscribe { track ->
                    Platform.runLater {
                        viewState.addGeneratedTrack(track)
                    }
                }
        } catch (e: Exception) {
            Platform.runLater {
                viewState.showErrorMessage("Couldn't read your file from disk")
            }
        }
    }

    override fun onInitialLoad() {
        musicInteractor.getAvailableNetworks().onErrorResumeWith {
            Platform.runLater {
                viewState.showErrorMessage(
                    "Couldn't load available networks for generation. " +
                            "You should check your connection"
                )
            }
        }.subscribe { networks ->
            Platform.runLater {
                viewState.loadNetworks(networks)
            }
        }
        musicInteractor.getAllTracksByAccount()
            .onErrorResumeWith {
                Platform.runLater {
                    viewState.showErrorMessage("Couldn't load your tracks")
                }
            }
            .subscribe { tracks ->
                Platform.runLater {
                    for (track in tracks) {
                        viewState.addGeneratedTrack(track)
                    }
                }
            }

    }

    fun setCurrentFile(file: File) {
        currentMusicFile = file
    }

    fun removeCurrentFile() {
        currentMusicFile = null
    }

    fun setCurrentNet(network: Network?) {
        currentNetwork = network
    }
}