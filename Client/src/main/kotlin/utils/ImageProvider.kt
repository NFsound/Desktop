package utils

import javafx.embed.swing.SwingFXUtils
import javafx.scene.image.Image
import javafx.scene.image.WritableImage
import models.utils.PlaylistImage
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.io.File
import java.util.*
import javax.imageio.ImageIO


object ImageProvider {

    fun getImage(filePath: String): Image {
        return Image(filePath)
    }

    fun getImage(playlistImage: PlaylistImage): Image{
        var image: BufferedImage
        val imageByte: ByteArray
        val decoder = Base64.getDecoder()
        imageByte = decoder.decode(playlistImage.sourceStringBase64)
        val bis = ByteArrayInputStream(imageByte)
        image = ImageIO.read(bis)
        bis.close()
        return SwingFXUtils.toFXImage(image,null)
    }

    fun makePlaylistImageFromFile(file: File):PlaylistImage{
        val bytes = file.readBytes()
        val encoder = Base64.getEncoder()
        return PlaylistImage(encoder.encodeToString(bytes))
    }

}