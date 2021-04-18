package network.api

import io.reactivex.rxjava3.core.Single
import models.core.account.*
import models.core.music.Playlist
import models.core.music.PlaylistUpdateResult
import models.wrappers.account.IdBody
import models.wrappers.music.*
import models.wrappers.networks.ListOfNetworks
import models.wrappers.news.NewsList
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface ApiService {

    //users

    @POST("register_user")
    fun registerUser(@Body accountRegistration: AccountRegistration): Single<RegistrationResult>

    @POST("get_user")
    fun getUserById(@Body idBody: IdBody): Single<UserInfo>

    @POST("authorize")
    fun loginUser(@Body accountLogin: AccountLogin): Single<LoginResult>


    //playlists

    @GET("get_user_playlists/{userId}")
    fun getUsersPlaylists(@Path("userId") userId: Int): Single<ListOfPlaylists>

    @POST("post_playlists")
    fun updatePlaylist(@Body linkPlaylist: LinkPlaylist): Single<PlaylistUpdateResult>

    @GET("get_playlists")
    fun getPopularPlaylists(): Single<ListOfPlaylists>


    //tracks

    @GET("get_tracks_by_user/{userId}")
    fun getAllTracksByUserId(@Path("userId") userId: Int): Single<TrackIdList>

    @POST("generate_post")
    fun sendGenerationRequest(
        @Body generationBody: GenerationBody
    ): Single<GenerationResponce>

    @GET("generate_get/{trackId}")
    @Streaming
    fun getTrackByIdGen(
        @Path("trackId") trackId: Int
    ):Call<ResponseBody>

    @GET("get_track/{trackId}")
    @Streaming
    fun getTrackByIdPub(
        @Path("trackId") trackId: Int
    ):Call<ResponseBody>


    //news
    @GET("news")
    fun getAllNews(): Single<NewsList>

    //utils
    @GET("get_networks")
    fun getAllAvailableNetworks(): Single<ListOfNetworks>

}