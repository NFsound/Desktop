package presentation.styles.sections


import javafx.scene.control.ScrollPane
import javafx.scene.paint.Color
import presentation.styles.Colors
import tornadofx.*

class NewsViewStyles : Stylesheet() {

    companion object {
        val mainScrollViewStyle by cssclass()
        val mainVBoxStyle by cssclass()
        val newsTitleStyle by cssclass()
        val newsTextStyle by cssclass()
        val newsVBoxStyle by cssclass()

        // const
        const val passwordFieldWidth = 140
        const val passwordFieldHeight = 28
    }

    init {
        mainScrollViewStyle {
            backgroundColor += Colors.mainColor
            borderInsets = multi(box(0.px, 0.px, 0.px, 0.px))
            padding = box(0.px)
            backgroundInsets = multi(box(0.px))
            vBarPolicy = ScrollPane.ScrollBarPolicy.NEVER
        }
        mainVBoxStyle {
            borderInsets = multi(box(0.px, 0.px, 0.px, 0.px))
            padding = box(0.px)
            backgroundInsets = multi(box(0.px))
            backgroundColor += Colors.mainColor
            vBarPolicy = ScrollPane.ScrollBarPolicy.NEVER
        }
        newsTitleStyle {
            fontSize = 18.pt
            padding = box(10.px)
            textFill = Colors.whiteColor
        }
        newsTextStyle {
            fontSize = 16.pt
            textFill = Colors.alternativeWhiteColor
            backgroundColor += Colors.mainColor
            borderInsets = multi(box(0.px))
            borderWidth = multi(box(0.px))
            borderColor = multi(box(Colors.mainColor))
            content{
                backgroundColor += Colors.mainColor
                borderInsets = multi(box(1.px))
                borderWidth = multi(box(1.px))
                borderColor = multi(box(Colors.mainColor))
                vBarPolicy = ScrollPane.ScrollBarPolicy.NEVER
            }
            scrollPane {
                vBarPolicy = ScrollPane.ScrollBarPolicy.NEVER
            }
        }

        newsVBoxStyle {
            borderColor += box(Colors.topBarBorderColor)
            borderWidth += box(0.px, 0.px, 1.px, 0.px)
        }
    }
}