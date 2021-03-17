package presentation.styles

import presentation.styles.Colors.alternativeColor
import presentation.styles.Colors.leftMenuItemSelectedColor
import presentation.styles.Colors.mainColor
import presentation.styles.Colors.topBarBorderColor
import tornadofx.*
class TopViewStyles: Stylesheet() {

    companion object{
        val topBarStyle by cssclass()

        //constants
        val topBarHeight = 40
    }

    init {
        topBarStyle{
            prefHeight = topBarHeight.px
            backgroundColor += leftMenuItemSelectedColor
            borderColor += box(leftMenuItemSelectedColor,
                leftMenuItemSelectedColor,
                topBarBorderColor,
                leftMenuItemSelectedColor)
        }
    }

}