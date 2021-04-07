package presentation.presenters.sections

import application.SonusApplication
import presentation.sections.account.AccountView
import presentation.sections.music.MusicView
import javax.inject.Inject

class MusicPresenter:SectionPresenter {
    @Inject
    lateinit var viewState: MusicView

    init {
        SonusApplication.getInstance().applicationComponent.inject(this)
    }
}