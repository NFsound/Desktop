package presentation.styles.sections
import javafx.geometry.Pos
import presentation.styles.Colors
import presentation.styles.sides.TopViewStyles
import tornadofx.*

class AccountViewStyles() :  Stylesheet(){

    companion object{
        val accountStyle by cssclass()
        val userInfoMainLabelStyle by cssclass()
        val userInfoLabelStyle by cssclass()
        val passwordFieldStyle by cssclass()
        val defaultLabelStyle by cssclass()
        val buttonDefaultStyle by cssclass()
        val buttonClickedStyle by cssclass()
        // const
        const val passwordFieldWidth = 140
        const val passwordFieldHeight = 28
    }

    init{
        accountStyle{
            padding = box(10.px)
        }
        userInfoLabelStyle{
            fontSize = 14.pt
            padding = box(10.px)
            textFill = Colors.alternativeWhiteColor
            alignment = Pos.CENTER_LEFT
        }
        userInfoMainLabelStyle{
            fontSize = 16.pt
            padding = box(10.px)
            textFill = Colors.whiteColor
        }

        passwordFieldStyle {
            backgroundRadius = multi(box(18.px, 18.px, 18.px, 18.px))
            prefWidth = passwordFieldWidth.px
            prefHeight = passwordFieldHeight.px
            padding = box(4.px)
            backgroundColor += Colors.whiteColor
            alignment = Pos.BOTTOM_CENTER
        }

        defaultLabelStyle{
            fontSize = 10.pt
            padding = box(10.px)
            textFill = Colors.alternativeWhiteColor
        }

        buttonDefaultStyle{
            textFill = Colors.whiteColor
            backgroundColor += Colors.bottomBarColor
            fontSize = 14.px
            padding = box(2.px)
            alignment = Pos.CENTER_RIGHT
        }
        buttonClickedStyle{
            textFill = Colors.alternativeColor
            borderColor += box(Colors.alternativeColor)
            borderWidth += box(1.px)
        }

    }



}