package presentation.navigation

import presentation.menu.item.MenuItem
import presentation.menu.list.Menu
import javax.inject.Inject

class NavigatorImpl @Inject constructor(var menu: Menu):Navigator {
    companion object{
        val startIndex = 0
    }

    override var currentItem = menu.menuList[startIndex]

    override fun navigateTo(menuItem: MenuItem) {
        currentItem = menuItem
    }
}