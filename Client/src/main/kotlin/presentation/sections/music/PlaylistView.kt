package presentation.sections.music

import javafx.scene.Parent
import models.core.Playlist
import tornadofx.*

class PlaylistView(val playlist: Playlist): View("Current playlist") {
    override val root: Parent = scrollpane {
        vbox {

        }
    }

}