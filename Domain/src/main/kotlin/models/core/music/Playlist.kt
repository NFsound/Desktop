package models.core.music

import models.utils.PlaylistImage

class Playlist (private val list:ArrayList<Track>, val name:String, val image:PlaylistImage){

    var currentIndex: Int = -1

    fun getAllTracks():List<Track>{
        return list
    }

    fun addTrack(track: Track){
        list.add(track)
    }

    fun removeTrack(track: Track){
        list.remove(track)
    }

    fun nextTrack(): Track {
        return if(currentIndex < list.size - 1){
            currentIndex += 1
            list[currentIndex]
        } else {
            currentIndex = 0
            list[currentIndex]
        }
    }

    fun previousTrack(): Track {
        return if(currentIndex > 0){
            currentIndex -= 1
            list[currentIndex]
        }else{
            currentIndex = list.size - 1
            list[currentIndex]
        }
    }

    fun nextRandomTrack(): Track {
        currentIndex = (0..list.size).random()
        return list[currentIndex]
    }

    fun getSize(): Int{
        return list.size
    }
    companion object{

        val emptyPlaylist = Playlist(arrayListOf(Track.emptyTrack()),"Some name",PlaylistImage())
    }

}