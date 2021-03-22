package utils

import javafx.scene.image.Image
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths

object ImageProvider {

    fun getImage(filePath: String): Image {
        return Image(filePath)
    }

}