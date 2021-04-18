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
        musicInteractor.getAllPlaylistsByAccount().subscribe { lists ->
            Platform.runLater {
                viewState.openPlaylistsView(icon, lists, track)
            }
        }
        //TODO ON ERROR
    }

    fun startTrackGeneration() {
        val byteArray = currentMusicFile!!.readBytes()
        musicInteractor
            .generateTrack(byteArray, GenerationParams("fv", "fv"))
            .subscribe { track ->
                Platform.runLater {
                    viewState.addGeneratedTrack(track)
                }
            }
        //TODO ON ERROR
    }

    override fun onInitialLoad() {
        Platform.runLater{
        musicInteractor.getAvailableNetworks().onErrorResumeWith {

        }.subscribe { networks ->
            Platform.runLater {
            viewState.loadNetworks(networks)
             }
        }
        //TODO ON ERROR
        musicInteractor.getAllTracksByAccount()
            .subscribe { tracks ->
                Platform.runLater {
                    for (track in tracks) {
                        viewState.addGeneratedTrack(track)
                    }
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