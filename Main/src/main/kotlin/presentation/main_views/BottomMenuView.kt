package presentation.main_views

import javafx.scene.Parent
import tornadofx.*

class BottomMenuView(): View() {


    override val root: Parent = hbox {
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