package repositories

import io.reactivex.rxjava3.core.Single
import models.core.GenerationParams
import models.core.Track
import models.wrappers.TrackList

interface TrackRepository {

    fun getAllTracks(accountId: Int): Single<List<Track>>

    fun postTrack(track: Track): Single<Int>

    fun generateTrack(generationParams: GenerationParams): Single<Track>

}