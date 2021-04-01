package presentation.styles

import javafx.scene.paint.Color
import presentation.styles.Colors.alternativeColor
import presentation.styles.Colors.bottomBarColor
import presentation.styles.Colors.mainColor
import presentation.styles.Colors.alternativeWhiteColor
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
        val sliderStyle by cssclass()
        val trackInfoStyle by cssclass()
        val textLabelStyle by cssclass()
        val authorTextLabelStyle by cssclass()
        val rightIconStyle by cssclass()
        val volumeLevelStyle by cssclass()
        val volumeSlider by cssclass()
        //constants
        val bottomBarHeight = 68
        val imageSize = 52.0
        val imagePadding = 8.0
        val iconsPadding = 16.0
        val progressBarPrefWidth = 600
        val volumeBarPrefWidth = 90
        val playerIconSize = 16
        val rightIconSize = 22
    }

    init {

        trackInfoStyle{
            minWidth = LeftMenuStyles.menuItemWidth.px
            maxWidth = LeftMenuStyles.menuItemWidth.px
        }


        bottomBarStyle {
            prefHeight = bottomBarHeight.px
            backgroundColor += bottomBarColor
        }
        playerStyle {
            padding = box(10.px)
        }
        progressBarStyle{
            accentColor = alternativeColor
            prefHeight = 6.px
            prefWidth = progressBarPrefWidth.px
            padding = box(0.px)
            bar{
                backgroundInsets = multi(box(0.px))
                backgroundRadius = multi(box(6.px))
                padding = box(0.px)
            }
            track{
                backgroundColor += mainColor
                backgroundInsets = multi(box(0.px))
                backgroundRadius = multi(box(6.px))
                padding = box(0.px)
            }
        }
        sliderStyle{
            prefWidth = progressBarPrefWidth.px
            track {
                backgroundColor = MultiValue(arrayOf(Color.TRANSPARENT))
            }
            thumb{
                backgroundInsets = multi(box(0.px))
                prefWidth = 0.px
            }
        }
        textLabelStyle{
            fontSize = 12.pt
            textFill = whiteColor
            padding = box(2.px)
        }
        authorTextLabelStyle{
            fontSize = 10.pt
            textFill = alternativeWhiteColor
            padding = box(2.px)
        }

        timeLabelStyle{
            padding = box(4.px)
            fontSize = 10.pt
            textFill = alternativeWhiteColor
            minWidth = 36.px
        }
        progressBoxStyle{
            padding = box(0.px)
        }

        rightIconStyle{
            padding = box(12.px)
        }

        volumeLevelStyle{
            accentColor = alternativeColor
            prefHeight = 6.px
            prefWidth = volumeBarPrefWidth.px
            padding = box(0.px)
            bar{
                backgroundInsets = multi(box(0.px))
                backgroundRadius = multi(box(6.px))
                padding = box(0.px)
            }
            track{
                backgroundColor += mainColor
                backgroundInsets = multi(box(0.px))
                backgroundRadius = multi(box(6.px))
                padding = box(0.px)
            }
        }

        volumeSlider{
            prefWidth = volumeBarPrefWidth.px
            track {
                backgroundColor = MultiValue(arrayOf(Color.TRANSPARENT))

            }

        }

    }

}