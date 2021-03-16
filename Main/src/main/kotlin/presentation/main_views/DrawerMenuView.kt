package presentation.main_views

import application.SonusApplication
import javafx.scene.layout.HBox
import presentation.menu.item.MenuItem
import presentation.menu.list.Menu
import presentation.styles.Colors
import presentation.styles.LeftMenuStyles
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
        addClass(LeftMenuStyles.leftMenuStyle)
        menuBoxes = ArrayList(menu.menuList.size)
        menu.menuList.forEach { item ->
            val hBox = makeHBox(item, index)
            this@vbox.add(hBox)
            menuBoxes.add(hBox)
            index += 1
        }
    }


    private fun handleMenuClick(menuItem: MenuItem, index: Int) {
        stackpane {
            for (i in menuBoxes.minus(menuItem).indices) {
                menuBoxes[i] = makeHBox(menu.menuList[i], i, false)
            }
            menuBoxes[index] = makeHBox(menu.menuList[index], index, true)
        }
        root.clear()
        root.children.addAll(menuBoxes)
    }


    private fun makeHBox(item: MenuItem, index: Int, isSelected: Boolean = false): HBox {
        return hbox {
            when (isSelected) {
                true -> addClass(LeftMenuStyles.leftMenuItemStyleSelected)
                false -> addClass(LeftMenuStyles.leftMenuItemStyleDefault)
            }
            setOnMouseClicked {
                handleMenuClick(item, index)
            }

            rectangle {
                width = LeftMenuStyles.rectangleWidth.toDouble()
                height = LeftMenuStyles.menuItemHeight.toDouble()
                when (isSelected) {
                    true -> fill = Colors.alternativeColor
                    false -> fill = Colors.leftBarColor
                }
            }

            stackpane {
                svgicon(
                    item.svgPath,
                    size = LeftMenuStyles.iconSize,
                    color = Colors.whiteColor
                ) {
                    setOnMouseClicked {
                        handleMenuClick(item, index)
                    }
                }
                padding = insets(10)
            }
            button(item.title) {
                when (isSelected) {
                    true -> addClass(LeftMenuStyles.leftMenuButtonStyleSelected)
                    false -> addClass(LeftMenuStyles.leftMenuButtonStyleDefault)
                }
                setOnMouseClicked {
                    handleMenuClick(item, index)
                }
                padding = insets(10)
            }

        }
    }

}
