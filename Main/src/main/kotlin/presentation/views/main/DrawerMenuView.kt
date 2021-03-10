package presentation.views.main

import application.SonusApplication
import presentation.menu.list.Menu
import presentation.styles.MainWindowStyles
import tornadofx.*
import javax.inject.Inject

class DrawerMenuView(): View() {
    @Inject
    lateinit var menu: Menu
    init {
        SonusApplication.getInstance().applicationComponent.inject(this)
    }
    /*addClass(MainWindowStyles.leftMenuStyle)
    menu.menuList.forEach {
        val item = ListMenuItem(it.title)
        item.addClass(MainWindowStyles.leftMenuItemStyle)
        this.add(item)
    }*/
    override var root = vbox{

        menu.menuList.forEach{
            hbox {
                rectangle{
                    addClass(MainWindowStyles.leftMenuRectangleStyle)
                }
                svgpath(it.svgPath){
                    addClass(MainWindowStyles.leftMenuIconStyle)
                }
                button(it.title){
                    addClass(MainWindowStyles.leftMenuItemStyle)
                }
            }

        }
        addClass(MainWindowStyles.leftMenuStyle)
    }
    
}