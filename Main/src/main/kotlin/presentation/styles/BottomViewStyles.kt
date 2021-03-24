package presentation.styles

import presentation.styles.Colors.alternativeColor
import presentation.styles.Colors.bottomBarColor
import presentation.styles.Colors.mainColor
import presentation.styles.Colors.whiteColor
import tornadofx.*



class BottomViewStyles: Stylesheet() {

    companion object{

        //styles
        val bottomBarStyle by cssclass()
        val playerStyle by cssclass()
        val progressBarStyle by cssclass()
        val timeLabelStyle by cssclass()
        val progressBoxStyle by cssclass()
        //constants
        val bottomBarHeight = 68
        val imageSize = 52.0
        val imagePadding = 8.0
    }

    init {
        bottomBarStyle {
            prefHeight = bottomBarHeight.px
            backgroundColor += bottomBarColor
        }
        playerStyle {
            //backgroundColor += alternativeColor
        }
        progressBarStyle{
            accentColor = alternativeColor
            prefHeight = 6.px
            bar{
                backgroundInsets = multi(box(0.px))
                backgroundRadius = multi(box(18.px, 18.px, 18.px, 18.px))
            }
            track{
                backgroundColor += mainColor
                backgroundRadius = multi(box(18.px, 18.px, 18.px, 18.px))
            }
        }
        timeLabelStyle{
            padding = box(10.px)
        }
        progressBoxStyle{

        }
    }

}