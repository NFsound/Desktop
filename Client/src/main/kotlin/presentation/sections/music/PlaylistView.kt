package presentation.sections.music

import javafx.geometry.NodeOrientation
import javafx.geometry.Pos
import javafx.geometry.Side
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.control.ContextMenu
import javafx.scene.layout.GridPane
import javafx.scene.layout.Priority
import models.core.Playlist
import models.core.Track
import presentation.presenters.main.CenterPresenter
import presentation.presenters.sections.SectionPresenter
import presentation.sections.common.Common.setMouseEnterBackground
import presentation.sections.common.Common.setMouseLeaveBackground
import presentation.sections.home.HomeViewImpl
import presentation.styles.Colors
import presentation.styles.sections.MusicViewStyles
import presentation.styles.sides.LeftMenuStyles
import tornadofx.*
import utils.IconsProvider

class PlaylistView(val playlist: Playlist, val presenter: CenterPresenter): View("Current playlist") {

    override val root: Parent = scrollpane {
        addClass(MusicViewStyles.playlistCreateMessageMainStyle)
        vbox {
            for (track in playlist.getAllTracks()){
                createTrackView(track)
            }
        }
    }

    fun Node.createTrackView(track: Track): GridPane {
        return gridpane{
            addClass(MusicViewStyles.trackBoxStyle)
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
                        IconsProvider.getSVGPath(HomeViewImpl.pauseIconFilePath),
                        size = LeftMenuStyles.iconSize,
                        color = Colors.alternativeWhiteColor
                    )
                    val playIcon = svgicon(
                        IconsProvider.getSVGPath(HomeViewImpl.playIconFilePath),
                        size = LeftMenuStyles.iconSize,
                        color = Colors.alternativeWhiteColor
                    )
                    setMouseEnterBackground(pauseIcon)
                    setMouseLeaveBackground(pauseIcon)
                    setMouseEnterBackground(playIcon)
                    setMouseLeaveBackground(playIcon)
                    pauseIcon.isVisible = false
                    setOnMouseClicked {

                    }
                }

                vbox {
                    alignment = Pos.CENTER
                    label(track.name) {
                        addClass(MusicViewStyles.trackNameLabelStyle)
                    }
                    label(track.authorName) {
                        addClass(MusicViewStyles.trackAuthorLabelStyle)
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
                    IconsProvider.getSVGPath(MusicViewImpl.plusIconFilePath),
                    size = LeftMenuStyles.iconSize,
                    color = Colors.alternativeWhiteColor
                ) {
                    alignment = Pos.CENTER_LEFT
                    padding = insets(10)

                    setOnMouseClicked {
                        createContextMenu().show(this, Side.LEFT,0.0,0.0)
                    }
                }
                setMouseEnterBackground(plusIcon)
                setMouseLeaveBackground(plusIcon)
            }
        }
    }


    fun Node.createContextMenu(): ContextMenu {
        return contextmenu {
            addClass(MusicViewStyles.contextMenuStyle)
            item("Playlist1") {
                checkbox {

                    //addClass()
                    action {
                        if (isSelected) {
                            //TODO add to playlist
                            isSelected
                        } else {
                            //TODO remove from playlist
                            isSelected
                        }
                    }
                }
                action {
                    this@contextmenu.hide()
                }
            }
            item("Create new playlist") {
                setOnAction {
                    openInternalWindow(
                        PlaylistCreateMessage(presenter),
                        owner = this@PlaylistView.root.parent
                    )
                    //TODO create playlist
                    this@contextmenu.hide()
                }

            }
        }
    }


}