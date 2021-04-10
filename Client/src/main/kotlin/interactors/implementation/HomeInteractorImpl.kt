package interactors.implementation

import interactors.HomeInteractor
import io.reactivex.rxjava3.core.Single
import models.core.Playlist
import repositories.AccountRepository
import repositories.PlaylistRepository
import repositories.TrackRepository
import javax.inject.Inject

class HomeInteractorImpl @Inject constructor(
    private val playlistRepository: PlaylistRepository,
    private val trackRepository: TrackRepository
): HomeInteractor {
    override fun getTrendPlaylists(): Single<List<Playlist>> {
        TODO("Not yet implemented")
    }

    override fun getRecommendationPlaylists(): Single<List<Playlist>> {
        TODO("Not yet implemented")
    }

    override fun getNewPlaylists(): Single<List<Playlist>> {
        TODO("Not yet implemented")
    }
}