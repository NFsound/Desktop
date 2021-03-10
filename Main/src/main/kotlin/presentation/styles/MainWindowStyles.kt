package presentation.styles



import tornadofx.*

class MainWindowStyles():Stylesheet() {

    companion object{
        //styles
        val bottomBarStyle by cssclass()
        val mainStyle by cssclass()
        val leftMenuItemStyle by cssclass()
        val leftMenuStyle by cssclass()
        val leftMenuIconStyle by cssclass()
        val leftMenuRectangleStyle by cssclass()


        //colors
        val mainColor = c("#000000", opacity = 0.85)
        val white = c("#FFFFFF")
        val bottomBarColor = c("#474040")
        val leftBarColor = c("#0D0C0C")
        val alternativeColor = c("#DB22DF")
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

        leftMenuItemStyle{
            textFill = white

        }

        leftMenuStyle{
            textFill = alternativeColor
            prefWidth = 150.px
        }

        leftMenuIconStyle{
            maxWidth = 5.px
            maxHeight = 5.px
            fill = alternativeColor
            backgroundColor += alternativeColor
        }

        leftMenuRectangleStyle{
            backgroundColor += alternativeColor
            minWidth = 2.px
            maxWidth = 2.px
        }

    }

}