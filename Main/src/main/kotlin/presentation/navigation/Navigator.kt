package presentation.navigation

import presentation.menu.item.MenuItem

interface Navigator {

    var currentItem:MenuItem

    fun navigateTo(menuItem: MenuItem)


}