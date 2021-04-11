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

        comboBoxStyle{
            backgroundColor += Colors.bottomBarColor
        }




    }

}