package presentation.styles

import presentation.styles.Colors.bottomBarColor
import tornadofx.*



class BottomViewStyles: Stylesheet() {

    companion object{

        //styles
        val bottomBarStyle by cssclass()


        //constants
        val bottomBarHeight = 68

    }

    init {
        bottomBarStyle {
            prefHeight = bottomBarHeight.px
            backgroundColor += bottomBarColor
        }

    }

}