package presentation.main_views.sides

import application.SonusApplication
import javafx.scene.control.Button
import javafx.scene.layout.HBox
import javafx.scene.shape.Rectangle
import presentation.menu.item.MenuItem
import presentation.menu.list.Menu
import presentation.presenters.main.CenterPresenter
import presentation.styles.Colors
import presentation.styles.LeftMenuStyles
import tornadofx.*
import javax.inject.Inject

class LeftMenuView() : View(), SideView {

    @Inject
    lateinit var menu: Menu

    private lateinit var menuBoxes: MutableList<HBox>

    /**
     * shared presenter (общий)
     */
    private lateinit var mainPresenter: CenterPresenter



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

    private fun manageItemViewSelection(index: Int, isSelected: Boolean) {
        val button = menuBoxes[index].children.find {
            it.id == "button${index}"
        } as Button
        val rectangle = menuBoxes[index].children.find {
            it.id == "rect${index}"
        } as Rectangle
        when (isSelected) {
            true -> {
                menuBoxes[index].removeClass(LeftMenuStyles.leftMenuItemStyleDefault)
                menuBoxes[index].addClass(LeftMenuStyles.leftMenuItemStyleSelected)
                button.removeClass(LeftMenuStyles.leftMenuButtonStyleDefault)
                button.addClass(LeftMenuStyles.leftMenuButtonStyleSelected)
                rectangle.fill = Colors.alternativeColor
            }
            false->{
                menuBoxes[index].removeClass(LeftMenuStyles.leftMenuItemStyleSelected)
                menuBoxes[index].addClass(LeftMenuStyles.leftMenuItemStyleDefault)
                button.removeClass(LeftMenuStyles.leftMenuButtonStyleSelected)
                button.addClass(LeftMenuStyles.leftMenuButtonStyleDefault)
                rectangle.fill = Colors.leftBarColor
            }
        }
    }

    /**
     * Вызывается презентером
     */
    fun setSection(menuItem: MenuItem, index:Int){
        for (i in menuBoxes.minus(menuItem).indices) {
            manageItemViewSelection(i,false)
        }
        manageItemViewSelection(index,true)
        root.applyCss()
    }

    /**
     * Сообщает презентеру о нажатии на меню
     */
    private fun handleMenuClick(menuItem: MenuItem, index: Int) {
        mainPresenter.onSectionSelected(menuItem, index)
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
                id = "rect$index"
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
                id = "button$index"
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

    override fun setPresenter(centerPresenter: CenterPresenter) {
        mainPresenter = centerPresenter
    }

}
