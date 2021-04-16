package presentation.sections.music

import application.SonusApplication
import javafx.collections.FXCollections.observableArrayList
import javafx.geometry.NodeOrientation
import javafx.geometry.Pos
import javafx.geometry.Side
import javafx.scene.Node
import javafx.scene.control.*
import javafx.scene.layout.*
import javafx.scene.text.TextAlignment
import javafx.stage.FileChooser
import models.core.music.Playlist
import models.core.networks.Network
import models.core.music.Track
import presentation.presenters.sections.MusicPresenter
import presentation.presenters.sections.SectionPresenter
import presentation.sections.account.MessageWindow
import presentation.sections.common.Common.createContextMenu
import presentation.sections.common.Common.setMouseEnterBackground
import presentation.sections.common.Common.setMouseLeaveBackground
import presentation.sections.common.PlaylistCreateMessage
import presentation.sections.home.HomeViewImpl.Companion.pauseIconFilePath
import presentation.sections.home.HomeViewImpl.Companion.playIconFilePath
import presentation.styles.Colors
import presentation.styles.sections.MusicViewStyles
import presentation.styles.sections.MusicViewStyles.Companion.contextMenuStyle
import presentation.styles.sections.MusicViewStyles.Companion.trackAuthorLabelStyle
import presentation.styles.sections.MusicViewStyles.Companion.trackBoxStyle
import presentation.styles.sections.MusicViewStyles.Companion.trackNameLabelStyle
import presentation.styles.sections.NewsViewStyles
import presentation.styles.sides.LeftMenuStyles
import tornadofx.*
import utils.IconsProvider

class MusicViewImpl() : View(), MusicView {


    companion object {
        val plusIconFilePath = SonusApplication.resourcePath + "/icons/plus_icon_path.txt"
    }

    override var sectionTitle = "Music"

    var fileLoaded = false

    //views
    lateinit var loadFileButton: Button
    lateinit var paramsComboBox: ComboBox<Network>
    lateinit var progressIndicator: ProgressIndicator
    lateinit var generateMusicButton: Button
    lateinit var genBox:VBox


    fun Node.createTrackView(track: Track): GridPane {
        return gridpane{
            addClass(trackBoxStyle)
            hbox {
                gridpaneConstraints {
                    columnRowIndex(0,0)
                }
                useMaxWidth = true
                padding = insets(4)
                //play pause
                stackpane {
                    alignment = Pos.CENTER
                    paddingHorizontal = 10
                    val pauseIcon = svgicon(
                        IconsProvider.getSVGPath(pauseIconFilePath),
                        size = LeftMenuStyles.iconSize,
                        color = Colors.alternativeWhiteColor
                    )
                    val playIcon = svgicon(
                        IconsProvider.getSVGPath(playIconFilePath),
                        size = LeftMenuStyles.iconSize,
                        color = Colors.alternativeWhiteColor
                    )
                    setMouseEnterBackground(pauseIcon)
                    setMouseLeaveBackground(pauseIcon)
                    setMouseEnterBackground(playIcon)
                    setMouseLeaveBackground(playIcon)
                    pauseIcon.isVisible = false
                    setOnMouseClicked {
                        musicPresenter.centerPresenter.onPlayTrackClicked(playIcon,pauseIcon,track)
                    }
                }

                vbox {
                    alignment = Pos.CENTER
                    label(track.name) {
                        addClass(trackNameLabelStyle)
                    }
                    label(track.authorName) {
                        addClass(trackAuthorLabelStyle)
                    }
                }
            }

            hbox {
                gridpaneConstraints {
                    columnRowIndex(1,0)
                    hGrow = Priority.ALWAYS
                    paddingHorizontal = 20
                }
                nodeOrientation = NodeOrientation.RIGHT_TO_LEFT
                val plusIcon = svgicon(
                    IconsProvider.getSVGPath(plusIconFilePath),
                    size = LeftMenuStyles.iconSize,
                    color = Colors.alternativeWhiteColor
                ) {
                    alignment = Pos.CENTER_LEFT
                    padding = insets(10)

                    setOnMouseClicked {
                        musicPresenter.onPlusIconClick(this@svgicon, track)
                    }
                }
                setMouseEnterBackground(plusIcon)
                setMouseLeaveBackground(plusIcon)
            }
        }
    }


    override fun openPlaylistsView(icon:SVGIcon,playlists: List<Playlist>, track: Track) {
        icon.createContextMenu(playlists, track, musicPresenter.centerPresenter,this)
            .show(icon, Side.LEFT, 0.0, 0.0)
    }
    override fun setPresenter(presenter: SectionPresenter) {
        musicPresenter = presenter as MusicPresenter
        musicPresenter.onInitialLoad()
    }

    override fun getPresenter(): SectionPresenter {
        return musicPresenter
    }

    override fun showErrorMessage(text: String) {
        openInternalWindow(
            MessageWindow(text),
            owner = this.root.parent.parent
        )
    }

    private lateinit var musicPresenter: MusicPresenter

    override val root: ScrollPane = scrollpane {
        addClass(NewsViewStyles.mainScrollViewStyle)
        isFitToWidth = true
        isFitToHeight = true

        vbox {
            addClass(NewsViewStyles.mainVBoxStyle)
            //generation
            vbox {
                alignment = Pos.TOP_CENTER
                label("Future has come. Generate your music here and now") {
                    textAlignment = TextAlignment.CENTER
                    addClass(MusicViewStyles.topLabelStyle)
                }
            }
            hbox {
                vbox {

                    label("Easy as one-two-three:") {
                        addClass(MusicViewStyles.smallLabelStyle)
                    }

                    hbox {
                        label("1) Load your favourite composition") {
                            addClass(MusicViewStyles.smallLabelStyle)
                        }
                        hbox {
                            alignment = Pos.CENTER

                            loadFileButton = button("Load file") {
                                addClass(MusicViewStyles.buttonMainDefaultStyle)
                                setOnMouseClicked {
                                    onLoadFileClicked()
                                }
                            }
                        }
                    }

                    hbox {
                        label("2) Choose your parameters") {
                            addClass(MusicViewStyles.smallLabelStyle)
                        }
                        hbox {
                            alignment = Pos.CENTER
                            paramsComboBox = combobox {
                                title = "Choose generation network"
                                converter = NetworkConverter()
                                addClass(MusicViewStyles.comboBoxStyle)
                                setOnAction {
                                    musicPresenter.setCurrentNet(selectedItem)
                                }
                            }
                        }
                    }

                    hbox {
                        label("3) Enjoy generated music") {
                            addClass(MusicViewStyles.smallLabelStyle)
                        }
                        hbox {
                            alignment = Pos.CENTER
                            progressIndicator = progressindicator {
                                addClass(MusicViewStyles.progressStyle)
                                isVisible = false
                            }
                            generateMusicButton = button("Generate!") {
                                addClass(MusicViewStyles.buttonMainDefaultStyle)
                                setOnMouseClicked {
                                    onGenerateButtonClicked()
                                }
                            }
                        }

                    }
                }
            }

            genBox = vbox {
                alignment = Pos.TOP_CENTER
                label("Here is what you have generated recently") {
                    textAlignment = TextAlignment.CENTER
                    addClass(MusicViewStyles.topLabelStyle)
                }
            }

        }

    }

    override fun loadNetworks(networks: List<Network>) {
        paramsComboBox.items = observableArrayList(networks)
    }

    override fun addGeneratedTrack(track: Track) {
        genBox.createTrackView(track)
    }

    fun onGenerateButtonClicked() {
        musicPresenter.startTrackGeneration()
    }


    private fun onLoadFileClicked() {
        if (!fileLoaded) {
            val extFilter = FileChooser.ExtensionFilter(
                "Music files (*.wav)",
                "*.wav"
            )
            try {
                musicPresenter.setCurrentFile(
                    chooseFile(
                        "Choose audio file",
                        arrayOf(extFilter),
                        mode = FileChooserMode.Single
                    )
                        .get(0)
                )
                loadFileButton.text = "Delete file"
                fileLoaded = true
            } catch (e: Exception) {
                //TODO workout
            }
        } else {
            musicPresenter.removeCurrentFile()
            loadFileButton.text = "Load file"
            fileLoaded = false
        }
    }

}