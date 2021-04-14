package interactors

import io.reactivex.rxjava3.core.Single
import models.core.Account
import models.core.Playlist

interface HomeInteractor {

    fun getPopularPlaylists(): Single<List<Playlist>>

    fun getMyPlaylists(account:Account):Single<List<Playlist>>

    fun createPlaylist(playlist: Playlist):Single<Boolean>

    fun filterPlaylists(text:String):Single<List<Playlist>>

}