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


        //colors
        val mainColor = c("#000000", opacity = 0.85)
        val white = c("#FFFFFF")
        val bottomBarColor = c("#474040")
        val leftBarColor = c("#0D0C0C")
        val alternativeColor = c("#DB22DF")

        //constants
        val menuItemHeight = 30
    }
    init {
        bottomBarStyle {
            prefHeight = 40.px
            backgroundColor += bottomBarColor
        }
        mainStyle{
            backgroundColor += mainColor
            prefHeight = 600.px
            prefWidth = 800.px
        }

        leftMenuItemStyleDefault{
            textFill = white
            backgroundColor += leftBarColor
        }
        leftMenuItemStyleSelected{
            backgroundColor.elements.removeAll { true }
            backgroundColor += mainColor
        }

        leftMenuStyle{
            backgroundColor += leftBarColor
            prefWidth = 150.px
        }

        leftMenuIconStyle{
            prefHeight = 30.px
            prefWidth = 30.px
            maxWidth = 60.px
            maxHeight = 60.px
            fill = white
        }

        iconStyle{
            padding = box(10.px)
        }

        leftMenuRectangleStyleSelected{
            fill = alternativeColor
        }

    }

}