package presentation.views.main

import application.SonusApplication
import javafx.geometry.Pos
import javafx.geometry.VPos
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.HBox
import javafx.scene.shape.Rectangle
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
                val indexL: Int = index
                setOnMouseClicked {
                    handleMenuClick(item, indexL)
                }

                rectangle {
                    id = "selection_rectangle${indexL}"
                    width = MainWindowStyles.rectangleWidth.toDouble()
                    height = MainWindowStyles.menuItemHeight.toDouble()
                    setOnMouseClicked {
                        handleMenuClick(item, indexL)
                    }
                }

                stackpane {
                    svgicon(
                        item.svgPath,
                        size = MainWindowStyles.iconSize,
                        color = MainWindowStyles.white
                    ) {

                        setOnMouseClicked {
                            handleMenuClick(item, indexL)
                        }
                    }
                    padding = insets(10)
                }


                button(item.title) {
                    addClass(MainWindowStyles.leftMenuItemStyleDefault)
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
        val rectangle = menuBoxes[index].children.find {
            it.id == "selection_rectangle${index}"
        } as Rectangle
        for (i in 0 until menuBoxes.size){
            val curRect = menuBoxes[i].children.find {
                it.id == "selection_rectangle${i}"
            } as Rectangle
            //curRect.addClass(MainWindowStyles.leftMenuRectangleStyleUnselected)
            curRect.fill = MainWindowStyles.leftBarColor
        }
        rectangle.fill = MainWindowStyles.alternativeColor
        //rectangle.addClass(MainWindowStyles.leftMenuRectangleStyleSelected)
    }

}