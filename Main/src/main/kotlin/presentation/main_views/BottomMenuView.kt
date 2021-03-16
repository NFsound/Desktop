package presentation.main_views

import javafx.scene.Parent
import presentation.styles.BottomViewStyles
import presentation.styles.MainWindowStyles
import tornadofx.*

class BottomMenuView(): View() {


    init{

    }

    override val root = hbox {
        addClass(BottomViewStyles.bottomBarStyle)
        var trackInfo = vbox{

        }
        var playerBox = vbox{
            hbox{
                //icons play/pause next previous
            }
            hbox{
                //progress
            }
        }
        var playlistIcon = svgpath {

        }
        var volumeIcon = svgpath{

        }
    }
}