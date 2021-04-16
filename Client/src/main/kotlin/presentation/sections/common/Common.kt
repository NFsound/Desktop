package presentation.sections.common

import javafx.geometry.Insets
import javafx.scene.Node
import javafx.scene.control.ContextMenu
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.CornerRadii
import models.core.music.Playlist
import models.core.music.Track
import presentation.presenters.main.CenterPresenter
import presentation.styles.Colors
import presentation.styles.sections.MusicViewStyles
import tornadofx.*

object Common {

    fun Node.createContextMenu(playlists: List<Playlist>,
                               track: Track,
                               mainPresenter:CenterPresenter,
    view:UIComponent): ContextMenu {
        return contextmenu {
            addClass(MusicViewStyles.contextMenuStyle)
            for (playlist in playlists) {
                item(playlist.name) {
                    checkbox {
                        if(playlists.contains(track)){
                            this.isSelected = true
                        }
                        action {
                            if (isSelected) {
                                mainPresenter.addToPlaylist(track, playlist)
                            } else {
                                mainPresenter.removeFromPlaylist(track, playlist)
                            }
                        }
                    }
                    action {
                        this@contextmenu.hide()
                    }
                }
            }
            item("Create new playlist") {
                setOnAction {
                    view.openInternalWindow(
                        PlaylistCreateMessage(mainPresenter),
                        owner = view.root.parent
                    )
                    this@contextmenu.hide()
                }

            }
        }
    }



    fun setMouseEnterBackground(icon: SVGIcon) {
        icon.setOnMouseEntered {
            icon.background = Background(
                BackgroundFill(
                    Colors.whiteColor,
                    CornerRadii.EMPTY, Insets.EMPTY
                )
            )
        }
    }

    fun setMouseLeaveBackground(icon: SVGIcon) {
        icon.setOnMouseExited {
            icon.background = Background(
                BackgroundFill(
                    Colors.alternativeWhiteColor,
                    CornerRadii.EMPTY, Insets.EMPTY
                )
            )
        }
    }


}