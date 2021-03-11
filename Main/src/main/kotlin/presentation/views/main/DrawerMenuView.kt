package presentation.views.main

import application.SonusApplication
import javafx.scene.layout.HBox
import presentation.menu.item.MenuItem
import presentation.menu.list.Menu
import presentation.styles.MainWindowStyles
import tornadofx.*
import javax.inject.Inject

class DrawerMenuView() : View() {
    @Inject
    lateinit var menu: Menu

    private lateinit var menuBoxes: MutableList<HBox>

    init {
        SonusApplication.getInstance().applicationComponent.inject(this)
    }


    override var root = vbox {
        var index = 0
        menuBoxes = ArrayList(menu.menuList.size)
        menu.menuList.forEach { item ->

            addClass(MainWindowStyles.leftMenuStyle)

            val hBox = hbox {
                addClass(MainWindowStyles.leftMenuStyle)

                rectangle {
                    id = "selection_rectangle"
                    width = 2.0
                    height = 30.0
                }

                region {
                    addClass(MainWindowStyles.leftMenuIconStyle)
                    svgicon(item.svgPath, size = 30, MainWindowStyles.white) {
                        addClass(MainWindowStyles.iconStyle)
                    }
                }

                button(item.title) {
                    addClass(MainWindowStyles.leftMenuItemStyleDefault)
                    val indexL:Int = index
                    setOnMouseClicked {
                        handleMenuClick(item, indexL)
                    }
                }
            }

            menuBoxes.add(hBox)
            index += 1
        }
    }

    private fun handleMenuClick(menuItem: MenuItem, index: Int) {
        val rectangle = menuBoxes[index].children.find { it -> it.id == "selection_rectangle" }
        for (item in menuBoxes) {
            item.addClass(MainWindowStyles.leftMenuItemStyleDefault)
        }
        menuBoxes[index].addClass(MainWindowStyles.leftMenuItemStyleSelected)
        rectangle!!.addClass(MainWindowStyles.leftMenuRectangleStyleSelected)
    }

}