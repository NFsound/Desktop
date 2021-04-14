package interactors.implementation

import interactors.HomeInteractor
import io.reactivex.rxjava3.core.Single
import models.core.account.Account
import models.core.music.Playlist
import repositories.AccountRepository
import repositories.PlaylistRepository
import javax.inject.Inject

class HomeInteractorImpl @Inject constructor(
    private val playlistRepository: PlaylistRepository,
    private val accountRepository: AccountRepository
): HomeInteractor {

    override fun getPopularPlaylists(): Single<List<Playlist>> {
        return playlistRepository.getPopularPlaylists()
    }

    override fun getMyPlaylists(account: Account): Single<List<Playlist>> {
        return accountRepository.getCurrentUser().flatMap {
            playlistRepository.getPlaylists(it.id)
        }
    }

    override fun createPlaylist(playlist: Playlist): Single<Boolean> {
        return playlistRepository.updatePlaylist(playlist)
    }

    override fun filterPlaylists(text: String): Single<List<Playlist>> {
        return playlistRepository.filterPlaylists(text)
    }

    override fun updatePlaylist(playlist: Playlist): Single<Boolean> {
        return playlistRepository.updatePlaylist(playlist)
    }


}