package repositories

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.AsyncSubject
import models.core.networks.GenerationParams
import models.core.music.Track

interface TrackRepository {

    fun getAllTracks(accountId: Int): Single<List<Track>>

    fun generateTrack(
        accountId: Int,
        generationParams: GenerationParams,
        byteArray: ByteArray
    ): Single<Track>

}