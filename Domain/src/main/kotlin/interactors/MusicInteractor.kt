package interactors

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.AsyncSubject
import models.core.music.Playlist
import models.core.music.Track
import models.core.networks.Network
import models.core.networks.GenerationParams

interface MusicInteractor {

    fun generateTrack(byteArray: ByteArray,
                      generationParams: GenerationParams): Single<Track>

    fun getAvailableNetworks():Single<List<Network>>

    fun getAllTracksByAccount():Single<List<Track>>

    fun getAllPlaylistsByAccount():Single<List<Playlist>>

}