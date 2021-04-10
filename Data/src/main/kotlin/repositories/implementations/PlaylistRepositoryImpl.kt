package repositories.implementations

import io.reactivex.rxjava3.core.Single
import models.core.Playlist
import network.api.ApiService
import repositories.PlaylistRepository
import javax.inject.Inject

class PlaylistRepositoryImpl @Inject constructor(private val api: ApiService): PlaylistRepository {
    override fun getPlaylists(userId: Int): Single<List<Playlist>> {
        TODO("Not yet implemented")
    }

    override fun getPlaylistByName(name: String): Single<List<Playlist>> {
        TODO("Not yet implemented")
    }

    override fun updatePlaylist(playlist: Playlist): Single<Boolean> {
        TODO("Not yet implemented")
    }
}