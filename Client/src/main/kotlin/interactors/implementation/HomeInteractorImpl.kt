package interactors.implementation

import interactors.HomeInteractor
import io.reactivex.rxjava3.core.Single
import models.core.Account
import models.core.Playlist
import repositories.AccountRepository
import repositories.PlaylistRepository
import repositories.TrackRepository
import javax.inject.Inject

class HomeInteractorImpl @Inject constructor(
    private val playlistRepository: PlaylistRepository,
    private val trackRepository: TrackRepository
): HomeInteractor {
    override fun getPopularPlaylists(): Single<List<Playlist>> {
        TODO("Not yet implemented")
    }

    override fun getMyPlaylists(account: Account): Single<List<Playlist>> {
        TODO("Not yet implemented")
    }

    override fun createPlaylist(playlist: Playlist): Single<Boolean> {
        TODO("Not yet implemented")
    }

    override fun filterPlaylists(text: String): Single<List<Playlist>> {
        TODO("Not yet implemented")
    }
}