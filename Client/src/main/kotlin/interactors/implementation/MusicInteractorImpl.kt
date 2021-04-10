package interactors.implementation

import interactors.MusicInteractor
import io.reactivex.rxjava3.core.Single
import models.core.Playlist
import repositories.AccountRepository
import repositories.PlaylistRepository
import repositories.TrackRepository
import repositories.UtilsRepository
import javax.inject.Inject

class MusicInteractorImpl @Inject constructor(
    private val accountRepository: AccountRepository,
    private val trackRepository: TrackRepository,
    private val playlistRepository: PlaylistRepository,
    private val utilsRepository: UtilsRepository
    ): MusicInteractor {
    override fun sendGenerationRequest(): Single<Boolean> {
        TODO("Not yet implemented")
    }

    override fun createPlaylist(playlist: Playlist): Single<Boolean> {
        TODO("Not yet implemented")
    }

    override fun updatePlaylist(playlist: Playlist): Single<Boolean> {
        TODO("Not yet implemented")
    }

    override fun getMyPlaylist(): Single<Playlist> {
        TODO("Not yet implemented")
    }
}