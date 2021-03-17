package presentation.styles



import presentation.styles.Colors.mainColor
import tornadofx.*

class MainWindowStyles():Stylesheet() {

    companion object{
        //styles
        val mainStyle by cssclass()
        //constants
        const val prefHeightMain = 650
        const val prefWidthMain = 1200
        const val minHeightMain = 600
        const val minWidthMain = 800
    }

    init {

        mainStyle{
            backgroundColor += mainColor
            prefHeight = prefHeightMain.px
            prefWidth = prefWidthMain.px
            minWidth = minWidthMain.px
            minHeight = minHeightMain.px
        }

    }

}