package interactors.implementation

import interactors.MusicInteractor
import io.reactivex.rxjava3.core.Single
import models.core.music.Playlist
import models.core.music.Track
import models.core.networks.Network
import models.core.networks.GenerationParams
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
) : MusicInteractor {


    override fun generateTrack(
        byteArray: ByteArray,
        generationParams: GenerationParams
    ): Single<Track> {
        return trackRepository.generateTrack(generationParams, byteArray)
    }

    override fun getAvailableNetworks(): Single<List<Network>> {
        return utilsRepository.getAllAvailableNetworks()
    }

    override fun getAllTracksByAccount(): Single<List<Track>> {
        return accountRepository.getCurrentUser()
            .flatMap { account->
                trackRepository.getAllTracks(account.id)
            }
    }

    override fun getAllPlaylistsByAccount(): Single<List<Playlist>> {
        return accountRepository.getCurrentUser()
            .flatMap { account->
                playlistRepository.getPlaylists(account.id)
            }

    }

}