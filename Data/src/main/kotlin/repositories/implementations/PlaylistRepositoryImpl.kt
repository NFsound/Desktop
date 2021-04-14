package repositories.implementations

import io.reactivex.rxjava3.core.Single
import models.core.Playlist
import network.api.ApiService
import repositories.PlaylistRepository
import javax.inject.Inject

class PlaylistRepositoryImpl @Inject constructor(
    private val api: ApiService
) : PlaylistRepository {

    var userPlaylists: List<Playlist> = emptyList()
    var popularPlaylists: List<Playlist> = emptyList()

    override fun getPlaylists(userId: Int): Single<List<Playlist>> {
        return api.getUsersPlaylists(userId)
            .map { it.list }.doAfterSuccess {
                userPlaylists = it
            }
    }

    override fun getPopularPlaylists(): Single<List<Playlist>> {
        return api.getPopularPlaylists()
            .map { it.list }
            .doAfterSuccess {
                popularPlaylists = it
            }
    }

    override fun updatePlaylist(playlist: Playlist): Single<Boolean> {
        return api.updatePlaylist(playlist)
            .map { true }
    }

    private fun checkFilter(playlist: Playlist,text: String):Boolean{
        return playlist.name.toLowerCase().contains(text.toLowerCase())
    }

    override fun filterPlaylists(text: String): Single<List<Playlist>> {
        return Single.just(popularPlaylists.filter { checkFilter(it,text) }
                + userPlaylists.filter { checkFilter(it,text) })
    }
}