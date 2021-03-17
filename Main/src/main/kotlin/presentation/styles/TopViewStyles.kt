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

        //constants
        val topBarHeight = 48
        val searchIconSize = 12
        val windowIconSize = 20
        val searchTextFieldWidth = 200
        val searchTextFieldHeight = 32
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
            backgroundRadius = multi(box(16.px))
            prefWidth = searchTextFieldWidth.px
            prefHeight = searchTextFieldHeight.px
            backgroundColor += whiteColor
            alignment = Pos.CENTER
        }

        searchTextLabelStyle {
            textFill = grayTextColor
            padding = box(5.px)
        }

        searchIconStyle {
            padding = box(5.px)
        }

        searchTextFieldWrapperStyle {
            padding = box(8.px)
        }

        iconWrapperStyle {
            padding = box(5.px, 10.px)
        }
        iconWrapperSelectedStyle {
            borderColor += box(topBarBorderColor)
            padding = box(5.px, 10.px)
        }

    }

}