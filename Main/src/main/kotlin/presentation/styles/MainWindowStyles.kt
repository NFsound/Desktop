package presentation.styles



import tornadofx.*

class MainWindowStyles():Stylesheet() {

    companion object{
        //styles
        val bottomBarStyle by cssclass()
        val mainStyle by cssclass()

        val leftMenuItemStyleDefault by cssclass()
        val leftMenuItemStyleSelected by cssclass()
        val leftMenuStyle by cssclass()
        val leftMenuIconStyle by cssclass()
        val iconStyle by cssclass()
        val leftMenuRectangleStyleSelected by cssclass()
        val leftMenuRectangleStyleUnselected by cssclass()

        //colors
        val mainColor = c("#000000", opacity = 0.85)
        val white = c("#FFFFFF")
        val bottomBarColor = c("#474040")
        val leftBarColor = c("#0D0C0C")
        val alternativeColor = c("#DB22DF")

        //constants
        val menuItemHeight = 50
        val menuItemWidth = 200
        val bottomBarHeight = 40
        val iconSize = 30
        val rectangleWidth = 2
    }
    init {
        bottomBarStyle {
            prefHeight = bottomBarHeight.px
            backgroundColor += bottomBarColor
        }
        mainStyle{
            backgroundColor += mainColor
            prefHeight = 650.px
            prefWidth = 1200.px
        }

        leftMenuItemStyleDefault{
            textFill = white
            fontSize = 18.px
            backgroundColor += leftBarColor
            prefHeight = menuItemHeight.px
        }
        leftMenuItemStyleSelected{
            backgroundColor.elements.removeAll { true }
            backgroundColor += mainColor
            prefHeight = menuItemHeight.px
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
            fill = white
        }

        leftMenuRectangleStyleSelected{
            fill = alternativeColor
        }
        leftMenuRectangleStyleUnselected{
            fill = leftBarColor
        }

    }

}