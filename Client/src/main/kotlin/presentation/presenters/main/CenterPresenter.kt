package presentation.presenters.main

import presentation.menu.item.MenuItem

interface CenterPresenter {

    fun selectSection(menuItem: MenuItem)

    fun pause()

    fun play()


}