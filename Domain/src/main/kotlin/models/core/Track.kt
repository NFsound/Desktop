package models.core

import java.rmi.server.UID
import java.util.*

class Track(
    val id: Int,
    val name: String,
    val authorId: Int,
    val resPath: String,
    val authorName: String
) {
    companion object {
        private val emptyTrack = Track(-1, "", 0, "", "")
        fun emptyTrack(): Track {
            return emptyTrack
        }
    }

    override fun toString(): String {
        return resPath
    }
}
