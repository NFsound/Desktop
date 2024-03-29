package presentation.main_views.sides

//import models.core.music.Track
import application.SonusApplication
import javafx.beans.binding.Bindings
import javafx.geometry.Pos
import javafx.geometry.Side
import javafx.scene.Node
import javafx.scene.control.ContextMenu
import javafx.scene.control.Label
import javafx.scene.control.ProgressBar
import javafx.scene.control.Slider
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.HBox
import javafx.scene.media.Media
import javafx.scene.media.MediaPlayer
import models.core.music.Playlist
import models.core.music.Track
import presentation.presenters.main.CenterPresenter
import presentation.sections.common.Common.createContextMenu
import presentation.sections.common.Common.setMouseEnterBackground
import presentation.sections.common.Common.setMouseLeaveBackground
import presentation.sections.common.PlaylistCreateMessage
import presentation.sections.common.PlaylistView
import presentation.styles.sides.BottomViewStyles
import presentation.styles.Colors.alternativeWhiteColor
import presentation.styles.Colors.whiteColor
import presentation.styles.sections.MusicViewStyles
import tornadofx.*
import utils.IconsProvider
import utils.ImageProvider
import javax.swing.Icon


class BottomMenuView() : View(), SideView {

    /**
     * shared presenter (общий)
     */
    private lateinit var mainPresenter: CenterPresenter


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
    lateinit var trackInfo:HBox

    //icons
    lateinit var playIcon: SVGIcon
    lateinit var pauseIcon: SVGIcon
    lateinit var volumeIcon: SVGIcon
    lateinit var playListIcon: SVGIcon
    lateinit var forwardIcon: SVGIcon
    lateinit var backwardIcon: SVGIcon
    lateinit var repeatIcon: SVGIcon
    lateinit var shuffleIcon: SVGIcon
    lateinit var plusIcon: SVGIcon

    var isPaused: Boolean = true


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
        const val plusIconFilePath: String =
            SonusApplication.resourcePath + "/icons/plus_icon_path.txt"

    }


    fun showPlaylistView(playlist: Playlist, allPlaylists: List<Playlist>) {
        openInternalWindow(
            PlaylistView(playlist, allPlaylists, mainPresenter),
            owner = this.root.parent
        )
    }


    fun makePlayerIcon(
        root: Node,
        iconFilePath: String,
        iconSize: Int = BottomViewStyles.playerIconSize,
        clickListener: () -> Unit = {}
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

    fun onPauseClick() {
        if (isPaused) {
            isPaused = false
            mainPresenter.onPlayClicked()
        } else {
            isPaused = true
            mainPresenter.onPauseClicked()
        }
    }

    private fun onShuffleClick() {
        mainPresenter.onShuffleClicked()
    }

    private fun onRepeatClick() {
        mainPresenter.onCycleClicked()
    }

    private fun onBackwardClick() {
        mainPresenter.onPreviousClicked()
    }

    private fun onForwardClick() {
        mainPresenter.onNextClicked()
    }

    private fun onPlusClick() {
        mainPresenter.onPlusClicked()
    }




    fun openPlaylistView(playlists: List<Playlist>, track: Track) {
        plusIcon.createContextMenu(playlists, track,mainPresenter,this)
            .show(plusIcon, Side.LEFT, 0.0, 0.0)
    }

    fun setTrackInfo(track: Track, player: MediaPlayer,
                     currentPlaylist: Playlist = Playlist.emptyPlaylist) {
        trackInfo.isVisible = true
        totalLengthLabel.isVisible = true
        passedTimeLabel.isVisible = true
        trackAuthorLabel.text = track.authorName
        trackNameLabel.text = track.name
        playlistImageView.image = ImageProvider.getImage(currentPlaylist.image)
        totalLengthLabel.text = convertTimeToMins(player.totalDuration.toSeconds())
        passedTimeLabel.text = convertTimeToMins(0.0)
    }

    fun convertTimeToMins(seconds:Double):String{
        val secs = (seconds % 60).toInt()
        val t1 = (seconds / 60).toInt().toString()
        val t2:String = if(secs < 10){
            "0${secs}"
        }else{
            "$secs"
        }
        return "$t1:$t2"
    }

    private fun onPlaylistIconClicked() {
        mainPresenter.onCurrentPlaylistClicked()
    }


    override val root = gridpane {
        addClass(BottomViewStyles.bottomBarStyle)

        //track info (left)
        trackInfo = hbox {
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
        trackInfo.isVisible = false

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
                    shuffleIcon = makePlayerIcon(
                        this, shuffleIconFilePath,
                        clickListener = this@BottomMenuView::onShuffleClick
                    )
                    backwardIcon = makePlayerIcon(
                        this, backwardIconFilePath,
                        clickListener = this@BottomMenuView::onBackwardClick
                    )
                    stackpane {

                        pauseIcon = makePlayerIcon(
                            this@stackpane, pauseIconFilePath,
                            BottomViewStyles.playerIconSize + 12
                        )
                        playIcon = makePlayerIcon(
                            this@stackpane, playIconFilePath,
                            BottomViewStyles.playerIconSize + 12
                        )
                        pauseIcon.isVisible = false

                        setMouseEnterBackground(pauseIcon)
                        setMouseLeaveBackground(playIcon)
                        setMouseEnterBackground(pauseIcon)
                        setMouseLeaveBackground(playIcon)
                        setOnMouseClicked {
                            this@BottomMenuView.onPauseClick()
                        }
                    }
                    forwardIcon = makePlayerIcon(
                        this, forwardIconFilePath,
                        clickListener = this@BottomMenuView::onForwardClick
                    )
                    repeatIcon = makePlayerIcon(
                        this, repeatIconFilePath,
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
                passedTimeLabel.isVisible = false
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
                totalLengthLabel.isVisible = false
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
                color = whiteColor,
                size = BottomViewStyles.rightIconSize
            ) {
                setOnMouseClicked {
                    this@BottomMenuView.onPlaylistIconClicked()
                }
            }
        }

        stackpane {
            addClass(BottomViewStyles.rightIconStyle)
            gridpaneConstraints {
                rowIndex = 0
                columnIndex = 3
                rowSpan = 2
            }
            plusIcon = svgicon(
                IconsProvider.getSVGPath(plusIconFilePath),
                color = whiteColor, size = BottomViewStyles.rightIconSize
            ) {
                setOnMouseClicked {
                    this@BottomMenuView.onPlusClick()
                }
            }
        }


        stackpane {
            addClass(BottomViewStyles.rightIconStyle)
            gridpaneConstraints {
                rowIndex = 0
                columnIndex = 4
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
                columnIndex = 5
                rowSpan = 2
            }
            volumeProgressBar = progressbar {
                addClass(BottomViewStyles.volumeLevelStyle)
                progress = 0.5
            }
            volumeSlider = slider(max = 1, min = 0.0, value = 1) {
                addClass(BottomViewStyles.volumeSlider)
            }
            volumeProgressBar.progressProperty().bind(volumeSlider.valueProperty())
        }


    }
}