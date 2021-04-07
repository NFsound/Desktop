package presentation.presenters.main

import application.SonusApplication
import presentation.main_views.BottomMenuView
import presentation.main_views.LeftMenuView
import presentation.main_views.TopMenuView
import presentation.main_views.center.CenterView
import presentation.menu.item.MenuItem
import presentation.navigation.Navigator
import javax.inject.Inject


class MainPresenter(): CenterPresenter {

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var centerViewState: CenterView

    @Inject
    lateinit var leftViewState: LeftMenuView

    @Inject
    lateinit var topViewState: TopMenuView

    @Inject
    lateinit var bottomViewState: BottomMenuView


    init {
        SonusApplication.getInstance().applicationComponent.inject(this)
    }

    override fun selectSection(menuItem: MenuItem){
        navigator.navigateTo(menuItem)
        centerViewState.selectSection(menuItem)
    }

    override fun pause() {

    }

    override fun play() {

    }

}