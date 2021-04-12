package presentation.styles.sections

import javafx.scene.paint.Color
import presentation.styles.Colors
import tornadofx.Stylesheet
import tornadofx.*

class MusicViewStyles : Stylesheet() {

    companion object {
        val topLabelStyle by cssclass()
        val smallLabelStyle by cssclass()
        val buttonMainDefaultStyle by cssclass()
        val comboBoxStyle by cssclass()
        val progressStyle by cssclass()
        val trackNameLabelStyle by cssclass()
        val trackAuthorLabelStyle by cssclass()
        val trackBoxStyle by cssclass()
        val test by cssclass()
    }

    init {
        topLabelStyle {
            fontSize = 20.pt
            padding = box(20.px)
            textFill = Colors.whiteColor
            backgroundColor += Color.TRANSPARENT
        }
        smallLabelStyle {
            fontSize = 14.pt
            padding = box(32.px)
            textFill = Colors.alternativeWhiteColor
            backgroundColor += Color.TRANSPARENT
        }
        buttonMainDefaultStyle {
            textFill = Colors.whiteColor
            backgroundColor += Colors.bottomBarColor
            fontSize = 10.pt
            padding = box(8.px)
        }

        trackBoxStyle{
            backgroundColor += Colors.mainColor
            borderColor = multi(box(Colors.whiteColor))
        }
        test{
            backgroundColor += Colors.alternativeColor
        }
        trackNameLabelStyle{
            textFill = Colors.whiteColor
            fontSize = 12.pt
            padding = box(2.px, 40.px)
        }
        trackAuthorLabelStyle{
            textFill = Colors.alternativeWhiteColor
            fontSize = 10.pt
            padding = box(2.px, 32.px)
        }


        comboBoxStyle{
            backgroundColor += Colors.bottomBarColor
                textFill = Colors.whiteColor
                fontSize = 12.pt
            listCell {
                textFill = Colors.whiteColor
                fontSize = 12.pt
                backgroundColor += Colors.bottomBarColor
            }
        }
        progressStyle{
            backgroundColor += Color.TRANSPARENT
            accentColor = Colors.alternativeColor
        }



    }

}