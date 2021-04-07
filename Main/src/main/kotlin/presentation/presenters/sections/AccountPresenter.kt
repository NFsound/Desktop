package presentation.presenters.sections

import application.SonusApplication
import presentation.sections.account.AccountView
import javax.inject.Inject

class AccountPresenter():SectionPresenter {

    @Inject
    lateinit var viewState: AccountView

    init {
        SonusApplication.getInstance().applicationComponent.inject(this)
    }
}