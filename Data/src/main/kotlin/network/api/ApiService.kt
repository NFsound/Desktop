package network.api

import io.reactivex.rxjava3.core.Single
import models.core.account.*
import models.core.music.Playlist
import models.core.music.PlaylistUpdateResult
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

    @GET("get_user")
    fun getUserById(@Body id: Int): Single<UserInfo>

    @GET("authorize")
    fun loginUser(@Body accountLogin: AccountLogin): Single<LoginResult>


    //playlists

    @GET("get_user_playlists")
    fun getUsersPlaylists(userId: Int): Single<ListOfPlaylists>

    @POST("post_playlists")
    fun updatePlaylist(@Body linkPlaylist: LinkPlaylist): Single<PlaylistUpdateResult>

    @GET("get_playlists")
    fun getPopularPlaylists(): Single<ListOfPlaylists>


    //tracks

    @GET("get_tracks_by_user")
    fun getAllTracksByUserId(id: Int): Single<TrackIdList>

    @POST("generate")
    fun sendGenerationRequest(
        @Body generationBody: GenerationBody
    ): Single<GenerationResponce>

    @GET("get_result_track")
    @Streaming
    fun getTrackById(
        trackId: Int
    ):Call<ResponseBody>



    //news
    @GET("news")
    fun getAllNews(): Single<NewsList>

    //utils
    @GET("get_networks")
    fun getAllAvailableNetworks(): Single<ListOfNetworks>

}