package repositories.implementations

import io.reactivex.rxjava3.core.Single
import models.core.networks.GenerationParams
import models.core.music.Track
import network.api.ApiService
import repositories.TrackRepository
import javax.inject.Inject

class TrackRepositoryImpl @Inject constructor(
    private val api: ApiService
) : TrackRepository {

    private var allUserTracks: ArrayList<Track> = ArrayList()


    override fun getAllTracks(accountId: Int): Single<List<Track>> {
        return api.getAllTracksByUserId(accountId).map {
                it.list
            }
            .doAfterSuccess {
                allUserTracks = it as ArrayList<Track>
            }.onErrorReturn {
                allUserTracks
            }
    }

    override fun generateTrack(
        generationParams: GenerationParams,
        byteArray: ByteArray
    ): Single<Track> {
        return api.generateTrack(byteArray, generationParams)
            .doAfterSuccess {
                allUserTracks.add(it)
            }
    }

}