package interactors

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import models.main.Song

interface SongInteractor {

    fun loadSong(song: Song): Completable

    fun makeQuery(songList: List<Song>): Single<Song>


}