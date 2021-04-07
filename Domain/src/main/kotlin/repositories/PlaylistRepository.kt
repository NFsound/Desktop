package repositories

import io.reactivex.rxjava3.core.Single
import models.core.Playlist
import models.wrappers.ListOfPlaylists

interface PlaylistRepository {

    fun getPlaylists(userId:Int): Single<ListOfPlaylists>

    fun getPlaylistByName(name:String): Single<ListOfPlaylists>

    fun updatePlaylist(playlist: Playlist): Single<Boolean>


}