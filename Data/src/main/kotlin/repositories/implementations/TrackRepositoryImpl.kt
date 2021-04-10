package repositories.implementations

import io.reactivex.rxjava3.core.Single
import models.core.GenerationParams
import models.core.Track
import network.api.ApiService
import repositories.TrackRepository
import javax.inject.Inject

class TrackRepositoryImpl @Inject constructor(private val api: ApiService): TrackRepository {
    override fun getAllTracks(accountId: Int): Single<List<Track>> {
        TODO("Not yet implemented")
    }

    override fun postTrack(track: Track): Single<Int> {
        TODO("Not yet implemented")
    }

    override fun generateTrack(generationParams: GenerationParams): Single<Track> {
        TODO("Not yet implemented")
    }
}