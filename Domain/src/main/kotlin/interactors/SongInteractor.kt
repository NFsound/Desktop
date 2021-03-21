package interactors

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import models.main.Track

interface SongInteractor {

    fun loadSong(track: Track): Completable

    fun makeQuery(trackList: List<Track>): Single<Track>


}