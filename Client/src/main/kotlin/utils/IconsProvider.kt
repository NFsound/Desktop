package utils

import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.streams.toList

object IconsProvider {

    fun getSVGPath(filepath:String):String{
        return Files.lines(Paths.get(filepath), StandardCharsets.UTF_8).toList()[0];
    }
}