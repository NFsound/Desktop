package repositories

import io.reactivex.rxjava3.core.Single
import models.core.Playlist
import models.wrappers.ListOfPlaylists

interface PlaylistRepository {

    fun getPlaylists(userId:Int): Single<List<Playlist>>

    fun getPlaylistByName(name:String): Single<List<Playlist>>

    fun updatePlaylist(playlist: Playlist): Single<Boolean>

}