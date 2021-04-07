package presentation.styles

import javafx.geometry.Pos
import presentation.styles.Colors.alternativeColor
import presentation.styles.Colors.grayTextColor
import presentation.styles.Colors.leftBarColor
import presentation.styles.Colors.leftMenuItemSelectedColor
import presentation.styles.Colors.topBarBorderColor
import presentation.styles.Colors.whiteColor
import tornadofx.*

class TopViewStyles : Stylesheet() {

    companion object {
        //styles
        val topBarStyle by cssclass()
        val topBarSettingsStyle by cssclass()
        val searchTextFieldStyle by cssclass()
        val searchTextFieldWrapperStyle by cssclass()
        val searchTextLabelStyle by cssclass()
        val searchIconStyle by cssclass()
        val iconWrapperStyle by cssclass()
        val iconWrapperSelectedStyle by cssclass()
        val searchIconWrapperStyle by cssclass()
        val textFieldStyle by cssclass()

        //constants
        const val topBarHeight = 48
        const val searchIconSize = 12
        const val windowIconSize = 18
        const val searchTextFieldWidth = 200
        const val searchTextFieldHeight = 32
    }

    init {
        topBarStyle {
            prefHeight = topBarHeight.px
            backgroundColor += leftMenuItemSelectedColor
            borderColor += box(topBarBorderColor)
            borderWidth += box(0.px, 0.px, 1.px, 0.px)
        }
        topBarSettingsStyle {
            backgroundColor += leftBarColor
            prefWidth = LeftMenuStyles.menuItemWidth.px
            prefHeight = topBarHeight.px
        }
        searchTextFieldStyle {
            backgroundRadius = multi(box(18.px, 18.px, 18.px, 18.px))
            prefWidth = searchTextFieldWidth.px
            prefHeight = searchTextFieldHeight.px
            backgroundColor += whiteColor
            alignment = Pos.CENTER_LEFT
        }
        textFieldStyle {
            borderWidth = multi(box(0.px))
            fontSize = 12.pt
            backgroundColor += whiteColor
            prefWidth = searchTextFieldWidth - (25+15).px
            padding = box(0.px,0.px,0.px,0.px)
        }
        searchTextLabelStyle {
            textFill = grayTextColor
            fontSize = 12.pt
            backgroundColor += whiteColor
        }

        searchIconWrapperStyle {
            prefHeight = searchTextFieldHeight.px
            prefWidth = searchTextFieldWidth.px
            backgroundColor += whiteColor
            backgroundRadius = multi(box(18.px, 18.px, 18.px, 18.px))
            padding = box(10.px)
        }
        searchIconStyle {
            padding = box(0.px, 5.px, 0.px, 10.px)
        }

        searchTextFieldWrapperStyle {
            padding = box(8.px)
        }

        iconWrapperStyle {
            padding = box(5.px, 12.px)
        }
        iconWrapperSelectedStyle {
            borderColor += box(topBarBorderColor)
            borderInsets = multi(box((-1).px))
            padding = box(5.px, 12.px)
        }

    }

}