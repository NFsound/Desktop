package network.api

import io.reactivex.rxjava3.core.Single
import models.core.account.*
import models.core.music.Playlist
import models.core.music.PlaylistUpdateResult
import models.core.music.Track
import models.core.networks.Network
import models.core.networks.GenerationParams
import models.core.news.News
import models.wrappers.playlist.ListOfPlaylists
import retrofit2.http.*


interface ApiService {

    //users
    @POST("register_user")
    fun registerUser(@Body accountRegistration: AccountRegistration): Single<RegistrationResult>

    @GET("get_user")
    fun getUserById(@Body id: Int): Single<UserInfo>

    @GET("")
    fun loginUser(@Body accountLogin: AccountLogin): Single<LoginResult>


    //playlists
    @GET("get_user_playlists")
    fun getUsersPlaylists(userId: Int): Single<ListOfPlaylists>

    @POST()
    fun updatePlaylist(@Body playlist: Playlist): Single<PlaylistUpdateResult>

    @GET()
    fun getPopularPlaylists(): Single<ListOfPlaylists>


    //tracks
    @GET()
    fun getAllTracksByUserId(@Body id: Int): Single<List<Track>>

    @POST()
    fun generateTrack(
        @Body byteArray: ByteArray,
        @Body generationParams: GenerationParams
    ): Single<Track>

    //news
    @GET("news")
    fun getAllNews(): Single<List<News>>

    //utils
    @GET("get_networks")
    fun getAllAvailableNetworks(): Single<List<Network>>

}