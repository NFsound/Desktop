package presentation.styles

import tornadofx.*
import presentation.styles.Colors.alternativeColor
import presentation.styles.Colors.leftBarColor
import presentation.styles.Colors.leftMenuItemSelectedColor
import presentation.styles.Colors.mainColor
import presentation.styles.Colors.whiteColor


class LeftMenuStyles:Stylesheet() {

    companion object {

        val leftMenuItemStyleDefault by cssclass()
        val leftMenuItemStyleSelected by cssclass()
        val leftMenuStyle by cssclass()
        val leftMenuIconStyle by cssclass()
        val leftMenuButtonStyleDefault by cssclass()
        val leftMenuButtonStyleSelected by cssclass()

        //constants
        val menuItemHeight = 50
        val menuItemWidth = 200
        val iconSize = 30
        val rectangleWidth = 2
    }

    init {

        leftMenuItemStyleDefault{
            backgroundColor += leftBarColor
            prefHeight = menuItemHeight.px
            prefWidth = menuItemWidth.px
        }

        leftMenuItemStyleSelected{
            backgroundColor += leftMenuItemSelectedColor
            prefHeight = menuItemHeight.px
            prefWidth = menuItemWidth.px
        }

        leftMenuButtonStyleDefault{
            textFill = whiteColor
            backgroundColor += leftBarColor
            fontSize = 18.px
        }

        leftMenuButtonStyleSelected{
            textFill = alternativeColor
            backgroundColor += leftMenuItemSelectedColor
            fontSize = 18.px
        }

        leftMenuStyle{
            backgroundColor += leftBarColor
            prefWidth = menuItemWidth.px
        }

        leftMenuIconStyle{
            prefHeight = menuItemHeight.px
            prefWidth = menuItemWidth.px
            maxWidth = 60.px
            maxHeight = 60.px
            fill = whiteColor
        }

    }



}