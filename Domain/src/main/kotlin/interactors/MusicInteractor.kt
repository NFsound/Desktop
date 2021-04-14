package interactors

import io.reactivex.rxjava3.core.Single
import models.core.*
import java.io.File

interface MusicInteractor {

    fun generateTrack(musicFile: File, generationParams: GenerationParams): Single<Track>

    fun getAvailableNetworks():Single<List<Network>>

    fun getAllTracksByAccount(account: Account):Single<List<Track>>

    fun getAllPlaylistsByAccount(account: Account):Single<List<Playlist>>

    fun filterMusic(text:String):Single<List<Track>>
}