package interactors

import io.reactivex.rxjava3.core.Single
import models.core.Playlist

interface HomeInteractor {

    fun getTrendPlaylists(): Single<List<Playlist>>

    fun getRecommendationPlaylists(): Single<List<Playlist>>

    fun getNewPlaylists(): Single<List<Playlist>>
}