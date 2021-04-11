package presentation.presenters.sections

import application.SonusApplication
import presentation.presenters.main.CenterPresenter
import presentation.sections.account.AccountView
import presentation.sections.music.MusicView
import javax.inject.Inject

class MusicPresenter:SectionPresenter {

    @Inject
    lateinit var viewState: MusicView

    override lateinit var centerPresenter: CenterPresenter

    init {
        SonusApplication.getInstance().applicationComponent.inject(this)
    }

    override fun filter(text: String) {

    }

    override fun onInitialLoad() {

    }
}