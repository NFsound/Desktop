package presentation.presenters.sections

import application.SonusApplication
import presentation.sections.account.AccountView
import presentation.sections.news.NewsView
import javax.inject.Inject

class NewsPresenter:SectionPresenter {
    @Inject
    lateinit var viewState: NewsView

    init {
        SonusApplication.getInstance().applicationComponent.inject(this)
    }
}