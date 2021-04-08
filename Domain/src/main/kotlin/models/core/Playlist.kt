package models.core

import models.utils.PlaylistImage

class Playlist (private val list:ArrayList<Track>, val image:PlaylistImage){
    init {
        if (list.isEmpty()){
            throw Exception()//TODO exception
        }
    }
    var currentIndex: Int = -1

    fun addTrack(track: Track){
        list.add(track)
    }

    fun removeTrack(track: Track){
        list.remove(track)
    }

    fun nextTrack(): Track{
        return if(currentIndex < list.size - 1){
            currentIndex += 1
            list[currentIndex]
        } else {
            currentIndex = 0
            list[currentIndex]
        }
    }

    fun previousTrack():Track{
        return if(currentIndex > 0){
            currentIndex -= 1
            list[currentIndex]
        }else{
            currentIndex = list.size - 1
            list[currentIndex]
        }
    }

    fun nextRandomTrack():Track{
        currentIndex = (0..list.size).random()
        return list[currentIndex]
    }

    fun getSize(): Int{
        return list.size
    }

}