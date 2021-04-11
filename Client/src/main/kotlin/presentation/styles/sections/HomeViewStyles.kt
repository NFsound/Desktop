package presentation.styles.sections

import javafx.scene.control.ScrollPane
import javafx.scene.paint.Color
import presentation.styles.Colors
import tornadofx.*

class HomeViewStyles: Stylesheet() {

    companion object{
        val popularScrollViewStyle by cssclass()
        val popularHBoxStyle by cssclass()
        val homeHostStyle by cssclass()
        val titleLabelStyle by cssclass()
        val playListStyle by cssclass()

        const val prefPlaylistSize = 240
    }
    init {
        popularScrollViewStyle{
            backgroundColor += Colors.mainColor
            borderInsets = multi(box(0.px, 0.px, 0.px, 0.px))
            padding = box(0.px)
            backgroundInsets = multi(box(0.px))
            hBarPolicy = ScrollPane.ScrollBarPolicy.NEVER
            vBarPolicy = ScrollPane.ScrollBarPolicy.NEVER
        }
        popularHBoxStyle{
            backgroundColor += Colors.mainColor
            borderInsets = multi(box(0.px, 0.px, 0.px, 0.px))
            padding = box(0.px)
            backgroundInsets = multi(box(0.px))
        }
        homeHostStyle{
            borderInsets = multi(box(0.px, 0.px, 0.px, 0.px))
            padding = box(0.px)
            backgroundInsets = multi(box(0.px))
            backgroundColor += Colors.mainColor
            hBarPolicy = ScrollPane.ScrollBarPolicy.NEVER
            vBarPolicy = ScrollPane.ScrollBarPolicy.NEVER
        }
        titleLabelStyle{
            fontSize = 18.pt
            padding = box(10.px)
            textFill = Colors.whiteColor
            backgroundColor += Color.TRANSPARENT
        }
        playListStyle{
            prefWidth = prefPlaylistSize.px
            prefHeight = prefPlaylistSize.px
        }


    }


}