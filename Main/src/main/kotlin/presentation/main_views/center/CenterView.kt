package presentation.main_views.center

import presentation.menu.item.MenuItem
import presentation.presenters.main.CenterPresenter
import presentation.presenters.main.MainPresenter

interface CenterView {

    fun selectSection(menuItem: MenuItem)

}