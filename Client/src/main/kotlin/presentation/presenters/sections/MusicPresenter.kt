package presentation.presenters.sections

import application.SonusApplication
import interactors.MusicInteractor
import javafx.application.Platform
import models.core.Network
import presentation.presenters.main.CenterPresenter
import presentation.sections.music.MusicView
import java.io.File
import javax.inject.Inject

class MusicPresenter:SectionPresenter {

    @Inject
    lateinit var viewState: MusicView

    @Inject
    lateinit var musicInteractor: MusicInteractor

    override lateinit var centerPresenter: CenterPresenter

    private var currentMusicFile:File? = null
    private var currentNetwork:Network? = null

    init {
        SonusApplication.getInstance().applicationComponent.inject(this)
    }

    override fun filter(text: String) {

    }

    fun startTrackGeneration(){

    }

    override fun onInitialLoad() {
        musicInteractor.getAvailableNetworks().subscribe {
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

    //fun createPlaylist()

}