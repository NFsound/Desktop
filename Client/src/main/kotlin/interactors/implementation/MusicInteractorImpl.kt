package interactors.implementation

import interactors.MusicInteractor
import io.reactivex.rxjava3.core.Single
import models.core.*
import models.core.account.Account
import repositories.AccountRepository
import repositories.PlaylistRepository
import repositories.TrackRepository
import repositories.UtilsRepository
import java.io.File
import javax.inject.Inject

class MusicInteractorImpl @Inject constructor(
    private val accountRepository: AccountRepository,
    private val trackRepository: TrackRepository,
    private val playlistRepository: PlaylistRepository,
    private val utilsRepository: UtilsRepository
    ): MusicInteractor {
    override fun generateTrack(byteArray: ByteArray, generationParams: GenerationParams): Single<Track> {
        TODO("Not yet implemented")
        //trackRepository.generateTrack()
    }

    override fun getAvailableNetworks(): Single<List<Network>> {
        TODO("Not yet implemented")
    }

    override fun getAllTracksByAccount(account: Account): Single<List<Track>> {
        TODO("Not yet implemented")
    }

    override fun getAllPlaylistsByAccount(account: Account): Single<List<Playlist>> {
        TODO("Not yet implemented")
    }

    override fun filterMusic(text: String): Single<List<Track>> {
        TODO("Not yet implemented")
    }

}