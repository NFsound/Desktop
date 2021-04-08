package models.core

import java.rmi.server.UID
import java.util.*

class Track(val id: Int, val name:String, val musicianId:Int, val resPath:String) {
    companion object{
        private val emptyTrack = Track(-1,"",0, "")
        fun emptyTrack():Track{
            return emptyTrack
        }
    }

    override fun toString(): String {
        return resPath
    }
}
