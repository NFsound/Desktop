package presentation.presenters.sections

import application.SonusApplication
import interactors.HomeInteractor
import presentation.sections.account.AccountView
import presentation.sections.home.HomeView
import javax.inject.Inject

class HomePresenter:SectionPresenter {
    @Inject
    lateinit var viewState: HomeView

    @Inject
    lateinit var homeInteractor: HomeInteractor

    init {
        SonusApplication.getInstance().applicationComponent.inject(this)
    }

    override fun filter(text: String) {

    }

    override fun onInitialLoad() {
        viewState.renderPopularPlaylists(emptyList())
    }

}