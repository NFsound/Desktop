package presentation.main_views

import application.SonusApplication
import com.sun.media.jfxmedia.MediaPlayer
import javafx.scene.Parent
//import models.main.Track
import presentation.styles.BottomViewStyles
import presentation.styles.Colors.whiteColor
import presentation.styles.MainWindowStyles
import tornadofx.*
import utils.IconsProvider
import javax.sound.midi.Track



class BottomMenuView(): View() {

    //var currentTrack: Track
    init{
      //  MediaPlayer
    }

    companion object{
        const val backwardIconFilePath: String =
            SonusApplication.resourcePath + "/icons/backward_icon_path.txt"
        const val forwardIconFilePath: String =
            SonusApplication.resourcePath + "/icons/forward_icon_path.txt"
        const val playIconFilePath: String =
            SonusApplication.resourcePath + "/icons/play_icon_path.txt"
        const val repeatIconFilePath: String =
            SonusApplication.resourcePath + "/icons/repeat_icon_path.txt"
        const val shuffleIconFilePath: String =
            SonusApplication.resourcePath + "/icons/shuffle_icon_path.txt"
    }

    override val root = hbox {
        addClass(BottomViewStyles.bottomBarStyle)
        var trackInfo = vbox{

        }
        var playerBox = vbox{
            hbox{
                //icons play/pause next previous
                svgicon(IconsProvider.getSVGPath(shuffleIconFilePath), size = 16,
                    color = whiteColor)
                svgicon(IconsProvider.getSVGPath(backwardIconFilePath), size = 16,
                    color = whiteColor)
                svgicon(IconsProvider.getSVGPath(playIconFilePath), size = 16,
                    color = whiteColor)
                svgicon(IconsProvider.getSVGPath(forwardIconFilePath), size = 16,
                    color = whiteColor)
                svgicon(IconsProvider.getSVGPath(repeatIconFilePath), size = 16,
                    color = whiteColor)
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