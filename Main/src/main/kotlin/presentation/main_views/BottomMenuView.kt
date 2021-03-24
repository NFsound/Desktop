package presentation.main_views

//import models.main.Track
import application.SonusApplication
import javafx.scene.control.Label
import javafx.scene.image.ImageView
import presentation.styles.BottomViewStyles
import presentation.styles.Colors.whiteColor
import tornadofx.*
import utils.IconsProvider
import utils.ImageProvider
import javax.sound.midi.Track


class BottomMenuView() : View() {

    lateinit var currentTrack: Track

    //ui components
    lateinit var trackImageView: ImageView
    lateinit var trackAuthorLabel: Label
    lateinit var trackNameLabel: Label
    lateinit var totalLengthLabel: Label
    lateinit var passedTimeLabel: Label

    init {
        //  MediaPlayer
    }

    companion object {
        const val backwardIconFilePath: String =
            SonusApplication.resourcePath + "/icons/backward_icon_path.txt"
        const val forwardIconFilePath: String =
            SonusApplication.resourcePath + "/icons/forward_icon_path.txt"
        const val playIconFilePath: String =
            SonusApplication.resourcePath + "/icons/play_icon_path.txt"
        const val playlistIconFilePath: String =
            SonusApplication.resourcePath + "/icons/playlist_icon_path.txt"
        const val repeatIconFilePath: String =
            SonusApplication.resourcePath + "/icons/repeat_icon_path.txt"
        const val shuffleIconFilePath: String =
            SonusApplication.resourcePath + "/icons/shuffle_icon_path.txt"
        const val pauseIconFilePath: String =
            SonusApplication.resourcePath + "/icons/pause_icon_path.txt"
    }

    override val root = gridpane {
        addClass(BottomViewStyles.bottomBarStyle)

        trackImageView = imageview {
            gridpaneConstraints {
                rowIndex = 0
                columnIndex = 0
            }
            fitHeight = BottomViewStyles.imageSize
            fitWidth = BottomViewStyles.imageSize
            paddingAll = BottomViewStyles.imagePadding
            image = ImageProvider.getImage("img/manul.jpg")
        }

        //track info
        vbox{
            gridpaneConstraints {
                rowIndex = 0
                columnIndex = 1
                rowSpan = 2
            }
            trackNameLabel = label {
                text = "Name"
            }
            trackAuthorLabel = label {
                text = "Author text"
            }
        }


        vbox {
            gridpaneConstraints {
                rowIndex = 0
                columnIndex = 2
                rowSpan = 2
            }
            addClass(BottomViewStyles.playerStyle)
            hbox {
                //icons play/pause next previous
                svgicon(
                    IconsProvider.getSVGPath(repeatIconFilePath), size = 16,
                    color = whiteColor
                ){

                }
                svgicon(
                    IconsProvider.getSVGPath(shuffleIconFilePath), size = 16,
                    color = whiteColor
                )
                svgicon(
                    IconsProvider.getSVGPath(backwardIconFilePath), size = 16,
                    color = whiteColor
                )
                svgicon(
                    IconsProvider.getSVGPath(playIconFilePath), size = 16,
                    color = whiteColor
                )
                svgicon(
                    IconsProvider.getSVGPath(forwardIconFilePath), size = 16,
                    color = whiteColor
                )

            }

            //progress
            hbox {
                addClass(BottomViewStyles.progressBoxStyle)
                passedTimeLabel = label {
                    addClass(BottomViewStyles.timeLabelStyle)
                    text= "0:05"
                }
                progressbar {
                    addClass(BottomViewStyles.progressBarStyle)
                    progress = 0.5
                }
                totalLengthLabel = label {
                    addClass(BottomViewStyles.timeLabelStyle)
                    text= "3:05"
                }
            }
        }


        var volumeIcon = svgpath {

        }
    }
}