package presentation.main_views

import presentation.presenters.main.CenterPresenter

interface SideView {

    fun setPresenter(centerPresenter: CenterPresenter)

}