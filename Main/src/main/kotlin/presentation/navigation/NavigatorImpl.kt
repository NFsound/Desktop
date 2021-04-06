package presentation.navigation

import application.SonusApplication
import presentation.menu.item.MenuItem
import presentation.menu.list.Menu
import javax.inject.Inject

class NavigatorImpl():Navigator {

    companion object{
        val startIndex = 0
    }

    @Inject
    lateinit var menu: Menu

    init {
        SonusApplication.getInstance().applicationComponent.inject(this)
    }

    override var currentItem = menu.menuList[startIndex]

    override fun navigateTo(menuItem: MenuItem) {
        currentItem = menuItem
    }
}