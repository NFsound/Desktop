package presentation.presenters.sections

import application.SonusApplication
import presentation.sections.account.AccountView
import presentation.sections.home.HomeView
import javax.inject.Inject

class HomePresenter:SectionPresenter {
    @Inject
    lateinit var viewState: HomeView

    init {
        SonusApplication.getInstance().applicationComponent.inject(this)
    }

    override fun filter(text: String) {
        viewState.filterView(text)
    }

}