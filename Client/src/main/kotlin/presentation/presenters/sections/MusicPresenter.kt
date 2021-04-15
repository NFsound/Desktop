package presentation.presenters.sections

import application.SonusApplication
import interactors.MusicInteractor
import javafx.application.Platform
import models.core.networks.GenerationParams
import models.core.networks.Network
import presentation.presenters.main.CenterPresenter
import presentation.sections.music.MusicView
import utils.ImageProvider
import java.io.File
import javax.inject.Inject

class MusicPresenter:SectionPresenter {

    @Inject
    lateinit var viewState: MusicView

    @Inject
    lateinit var musicInteractor: MusicInteractor

    override lateinit var centerPresenter: CenterPresenter

    private var currentMusicFile:File? = null
    private var currentNetwork: Network? = null

    init {
        SonusApplication.getInstance().applicationComponent.inject(this)
    }

    override fun filter(text: String) {

    }

    fun startTrackGeneration(){
        val byteArray = currentMusicFile!!.readBytes()
        musicInteractor
            .generateTrack(byteArray, GenerationParams("fv","fv"))
            .subscribe {
                track->
                Platform.runLater {
                    viewState.addGeneratedTrack(track)
                }
            }
    }

    override fun onInitialLoad() {
        musicInteractor.getAvailableNetworks().onErrorResumeWith {

        }.subscribe {
            networks->
            Platform.runLater {
                viewState.loadNetworks(networks)
            }
        }
    }

    fun setCurrentFile(file: File){
        currentMusicFile = file
    }

    fun removeCurrentFile(){
        currentMusicFile = null
    }

    fun setCurrentNet(network: Network?){
        currentNetwork = network
    }
}