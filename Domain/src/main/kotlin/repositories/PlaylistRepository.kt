package repositories

import io.reactivex.rxjava3.core.Single
import models.core.music.Playlist

interface PlaylistRepository {

    fun getPlaylists(userId:Int): Single<List<Playlist>>

    fun getPopularPlaylists():Single<List<Playlist>>

    fun updatePlaylist(playlist: Playlist): Single<Boolean>

    fun filterPlaylists(text:String): Single<List<Playlist>>
}