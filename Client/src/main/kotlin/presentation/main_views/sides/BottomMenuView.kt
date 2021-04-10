package presentation.main_views.sides

//import models.core.Track
import application.SonusApplication
import javafx.beans.binding.Bindings
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Node
import javafx.scene.control.Label
import javafx.scene.control.ProgressBar
import javafx.scene.control.Slider
import javafx.scene.image.ImageView
import javafx.scene.layout.*
import javafx.scene.media.Media
import javafx.scene.media.MediaPlayer
import models.core.Track
import presentation.presenters.main.CenterPresenter
import presentation.styles.sides.BottomViewStyles
import presentation.styles.Colors.alternativeWhiteColor
import presentation.styles.Colors.whiteColor
import tornadofx.*
import utils.IconsProvider
import utils.ImageProvider


class BottomMenuView() : View(), SideView {

    /**
     * shared presenter (общий)
     */
    private lateinit var mainPresenter:CenterPresenter


    override fun setPresenter(centerPresenter: CenterPresenter) {
        mainPresenter = centerPresenter
    }

    //ui components
    lateinit var playlistImageView: ImageView
    lateinit var trackAuthorLabel: Label
    lateinit var trackNameLabel: Label
    lateinit var totalLengthLabel: Label
    lateinit var passedTimeLabel: Label
    lateinit var trackProgressBar: ProgressBar
    lateinit var slider: Slider
    lateinit var volumeSlider: Slider
    lateinit var volumeProgressBar: ProgressBar
    //icons
    lateinit var playIcon: SVGIcon
    lateinit var pauseIcon: SVGIcon
    lateinit var volumeIcon: SVGIcon
    lateinit var playListIcon: SVGIcon
    lateinit var forwardIcon: SVGIcon
    lateinit var backwardIcon: SVGIcon
    lateinit var repeatIcon: SVGIcon
    lateinit var shuffleIcon: SVGIcon

    var isPaused:Boolean = true


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
        const val volumeIconFilePath: String =
            SonusApplication.resourcePath + "/icons/volume_icon_path.txt"
    }

    fun setMouseEnterBackground(icon:SVGIcon){
        icon.setOnMouseEntered {
            icon.background = Background(BackgroundFill(whiteColor,
                CornerRadii.EMPTY, Insets.EMPTY))
        }
    }

    fun setMouseLeaveBackground(icon:SVGIcon){
        icon.setOnMouseExited {
            icon.background = Background(BackgroundFill(alternativeWhiteColor,
                CornerRadii.EMPTY, Insets.EMPTY))
        }
    }


    fun makePlayerIcon(
        root:Node,
        iconFilePath: String,
        iconSize: Int = BottomViewStyles.playerIconSize,
        clickListener: ()->Unit = {}
    ): SVGIcon {
        val icon = svgicon(
            IconsProvider.getSVGPath(iconFilePath),
            size = iconSize,
            color = alternativeWhiteColor
        )
        setMouseEnterBackground(icon)
        setMouseLeaveBackground(icon)
        icon.setOnMouseClicked {
            clickListener()
        }
        val pane = stackpane {
            this.add(icon)
            padding = insets(0, BottomViewStyles.iconsPadding, 0, BottomViewStyles.iconsPadding)
        }
        root.add(pane)
        return icon
    }

    fun onPauseClick(){
        if (isPaused) {
            playIcon.isVisible = false
            pauseIcon.isVisible = true
            isPaused = false
            pauseIcon.parent.toFront()
            mainPresenter.onPlayClicked()
        }
        else{
            playIcon.isVisible = true
            pauseIcon.isVisible = false
            isPaused = true
            playIcon.parent.toFront()
            mainPresenter.onPauseClicked()
        }

    }

    fun onShuffleClick(){
        mainPresenter.onShuffleClicked()
    }
    fun onRepeatClick(){
        mainPresenter.onCycleClicked()
    }
    fun onBackwardClick(){
        mainPresenter.onPreviousClicked()
    }
    fun onForwardClick(){
        mainPresenter.onNextClicked()
    }

    fun setTrackInfo(track: Track, player: MediaPlayer){
        trackAuthorLabel.text = track.authorName
        trackNameLabel.text = track.name
        val media = Media(track.resPath)
        totalLengthLabel.text = media.duration.toString()
        media.markers
        slider.maxProperty().bind(
            Bindings.createDoubleBinding(
                { player.totalDuration.toSeconds() },
                player.totalDurationProperty()
            ))
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
            playlistImageView = imageview {

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
                    shuffleIcon = makePlayerIcon(this, shuffleIconFilePath,
                    clickListener = this@BottomMenuView::onShuffleClick
                    )
                    backwardIcon = makePlayerIcon(this, backwardIconFilePath,
                        clickListener = this@BottomMenuView::onBackwardClick
                        )
                    stackpane {

                        pauseIcon = makePlayerIcon(this@stackpane, pauseIconFilePath,
                            BottomViewStyles.playerIconSize + 12)
                        playIcon = makePlayerIcon(
                            this@stackpane, playIconFilePath,
                            BottomViewStyles.playerIconSize + 12)
                        pauseIcon.isVisible = false

                        setMouseEnterBackground(pauseIcon)
                        setMouseLeaveBackground(playIcon)
                        setMouseEnterBackground(pauseIcon)
                        setMouseLeaveBackground(playIcon)
                        setOnMouseClicked {
                            this@BottomMenuView.onPauseClick()
                        }
                    }
                    forwardIcon = makePlayerIcon(this, forwardIconFilePath,
                        clickListener = this@BottomMenuView::onForwardClick
                        )
                    repeatIcon = makePlayerIcon(this, repeatIconFilePath,
                        clickListener = this@BottomMenuView::onRepeatClick
                        )
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

        stackpane {
            addClass(BottomViewStyles.rightIconStyle)
            gridpaneConstraints {
                rowIndex = 0
                columnIndex = 2
                rowSpan = 2
            }
            playListIcon = svgicon(
                IconsProvider.getSVGPath(playlistIconFilePath),
                color = whiteColor, size = BottomViewStyles.rightIconSize
            )
        }

        stackpane {
            addClass(BottomViewStyles.rightIconStyle)
            gridpaneConstraints {
                rowIndex = 0
                columnIndex = 3
                rowSpan = 2
            }
            volumeIcon = svgicon(
                IconsProvider.getSVGPath(volumeIconFilePath),
                color = whiteColor, size = BottomViewStyles.rightIconSize
            )
        }

        stackpane {
            gridpaneConstraints {
                rowIndex = 0
                columnIndex = 4
                rowSpan = 2
            }
            volumeProgressBar = progressbar {
                addClass(BottomViewStyles.volumeLevelStyle)
                progress = 0.5
            }
            volumeSlider = slider(max = 1, min = 0.0, value = 0) {
                addClass(BottomViewStyles.volumeSlider)
            }
            volumeProgressBar.progressProperty().bind(volumeSlider.valueProperty())
        }



    }
}