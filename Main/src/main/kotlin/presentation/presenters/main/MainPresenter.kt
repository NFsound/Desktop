package presentation.presenters.main

import application.SonusApplication
import presentation.main_views.center.CenterView
import presentation.menu.item.MenuItem
import presentation.navigation.Navigator
import javax.inject.Inject


class MainPresenter(): CenterPresenter {

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var viewState: CenterView

    init {
        SonusApplication.getInstance().applicationComponent.inject(this)
    }

    fun selectSection(menuItem: MenuItem){
        navigator.navigateTo(menuItem)
        viewState.selectSection(menuItem)
    }

}