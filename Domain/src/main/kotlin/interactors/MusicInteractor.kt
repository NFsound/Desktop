package interactors

import io.reactivex.rxjava3.core.Single
import models.core.Network
import models.core.Playlist

interface MusicInteractor {

    fun sendGenerationRequest(/*params*/): Single<Boolean>

    fun createPlaylist(playlist: Playlist): Single<Boolean>

    fun updatePlaylist(playlist: Playlist): Single<Boolean>
    
    fun getMyPlaylist():Single<Playlist>

    fun getAllNetworks():Single<List<Network>>
}