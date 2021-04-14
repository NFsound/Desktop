package presentation.sections.common

import javafx.geometry.Pos
import javafx.scene.Parent
import javafx.scene.image.Image
import javafx.stage.FileChooser
import models.utils.PlaylistImage
import presentation.presenters.main.CenterPresenter
import presentation.presenters.sections.SectionPresenter
import presentation.styles.sections.AccountViewStyles
import presentation.styles.sections.HomeViewStyles
import presentation.styles.sections.MusicViewStyles
import presentation.styles.sides.BottomViewStyles
import tornadofx.*
import utils.ImageProvider
import java.io.File


class PlaylistCreateMessage(
    private var centerPresenter: CenterPresenter
) : View("Create playlist") {

    var currentPicture: PlaylistImage = PlaylistImage()

    fun loadPicture(): File {
        val extFilter = FileChooser.ExtensionFilter(
            "Image files (*.png)",
            "*.png"
        )
        return chooseFile(
            "Choose picture",
            arrayOf(extFilter),
            mode = FileChooserMode.Single
        ).get(0)
    }

    override val root: Parent = vbox {

        addClass(MusicViewStyles.playlistCreateMessageMainStyle)
        padding = insets(4)
        stackpane {
            val image = imageview {
                fitHeight = HomeViewStyles.prefPlaylistSize.toDouble()
                fitWidth = HomeViewStyles.prefPlaylistSize.toDouble()
                paddingAll = BottomViewStyles.imagePadding
                addClass(HomeViewStyles.imagePlaylistStyle)

            }
            button("Load image") {
                alignment = Pos.CENTER
                addClass(MusicViewStyles.buttonMainDefaultStyle)
                action {
                    val pictire = loadPicture()
                    image.image = Image(pictire.toURI().toString())
                    currentPicture = ImageProvider.makePlaylistImageFromFile(pictire)
                }
            }
        }
        anchorpane {

            val textField = textfield("Playlist name") {
                addClass(AccountViewStyles.passwordFieldStyle)
                padding = insets(10)
                anchorpaneConstraints {
                    topAnchor = 8
                    leftAnchor = 10
                    bottomAnchor = 8
                }
            }
            button("Create") {
                addClass(MusicViewStyles.buttonMainDefaultStyle)
                anchorpaneConstraints {
                    rightAnchor = 4
                }
                padding = insets(10)
                action {
                    centerPresenter.createPlaylist(textField.text,currentPicture)
                    close()
                }
            }


        }
    }
}