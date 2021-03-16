package presentation.styles



import presentation.styles.Colors.mainColor
import tornadofx.*

class MainWindowStyles():Stylesheet() {

    companion object{
        //styles
        val mainStyle by cssclass()

    }

    init {

        mainStyle{
            backgroundColor += mainColor
            prefHeight = 650.px
            prefWidth = 1200.px
        }

    }

}