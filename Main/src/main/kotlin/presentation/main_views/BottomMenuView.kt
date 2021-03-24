package presentation.main_views

//import models.main.Track
import application.SonusApplication
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.control.ProgressBar
import javafx.scene.control.Slider
import javafx.scene.image.ImageView
import javafx.scene.layout.StackPane
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
    lateinit var trackProgressBar: ProgressBar
    lateinit var slider: Slider

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


    fun makePlayerIcon(iconFilePath: String, iconSize:Int = BottomViewStyles.playerIconSize/*, clickListener: ()->Unit*/): StackPane {
        return stackpane {
            svgicon(
                IconsProvider.getSVGPath(iconFilePath),
                size = iconSize,
                color = whiteColor
            )
            padding = insets(0,BottomViewStyles.iconsPadding,0,BottomViewStyles.iconsPadding)
        }
    }


    override val root = gridpane {
        addClass(BottomViewStyles.bottomBarStyle)

        //track info (left)
        hbox {
            addClass(BottomViewStyles.trackInfoStyle)
            gridpaneConstraints {
                rowIndex = 0
                columnIndex = 0
                rowSpan = 2
            }
            //track image
            trackImageView = imageview {

                fitHeight = BottomViewStyles.imageSize
                fitWidth = BottomViewStyles.imageSize
                paddingAll = BottomViewStyles.imagePadding
                image = ImageProvider.getImage("img/manul.jpg")
            }

            //track info
            vbox {
                trackNameLabel = label {
                    text = "NameNAMENAME"
                    addClass(BottomViewStyles.textLabelStyle)
                }
                trackAuthorLabel = label {
                    text = "Author text"
                    addClass(BottomViewStyles.authorTextLabelStyle)
                }
            }
        }

        //player (center)
        vbox {
            gridpaneConstraints {
                rowIndex = 0
                columnIndex = 1
                rowSpan = 2
            }
            addClass(BottomViewStyles.playerStyle)
            stackpane {
                hbox(alignment = Pos.CENTER) {
                    //icons play/pause next previous
                    this.add(makePlayerIcon(shuffleIconFilePath))
                    this.add(makePlayerIcon(backwardIconFilePath))
                    this.add(makePlayerIcon(playIconFilePath, BottomViewStyles.playerIconSize + 12))
                    this.add(makePlayerIcon(forwardIconFilePath))
                    this.add(makePlayerIcon(repeatIconFilePath))
                }
            }

            //progress
            hbox {
                addClass(BottomViewStyles.progressBoxStyle)
                passedTimeLabel = label {
                    addClass(BottomViewStyles.timeLabelStyle)
                    text = "0:05"
                }
                stackpane {
                    trackProgressBar = progressbar {
                        addClass(BottomViewStyles.progressBarStyle)
                        progress = 0.5
                    }
                    slider = slider(max = 1, min = 0.0, value = 0) {
                        addClass(BottomViewStyles.sliderStyle)
                    }
                    trackProgressBar.progressProperty().bind(slider.valueProperty())
                }
                totalLengthLabel = label {
                    addClass(BottomViewStyles.timeLabelStyle)
                    text = "3:05"
                }
            }
        }



        var volumeIcon = svgpath {

        }
    }
}