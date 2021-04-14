package network.api
import io.reactivex.rxjava3.core.Single
import models.core.*
import models.core.account.AccountLogin
import models.core.account.AccountRegistration
import models.core.account.LoginResult
import models.core.account.RegistrationResult
import models.wrappers.account.AccountListWrapper
import models.wrappers.account.LoginBody
import models.wrappers.playlist.ListOfPlaylists
import models.wrappers.playlist.PlaylistUpdateResult
import retrofit2.http.*


interface ApiService {

    //users
    @POST("register_user")
    fun registerUser(@Body accountRegistration: AccountRegistration): Single<RegistrationResult>

    @GET("get_user")
    fun getUserById(@Body id: Int):Single<UserInfo>

    @GET("")
    fun loginUser(@Body accountLogin: AccountLogin): Single<LoginResult>



    //playlists
    @GET("get_user_playlists")
    fun getUsersPlaylists(userId:Int):Single<ListOfPlaylists>

    @GET("get_playlists")
    fun getAllPlayLists():Single<ListOfPlaylists>

    @POST()
    fun updatePlaylist(@Body playlist: Playlist): Single<PlaylistUpdateResult>

    @GET()
    fun getPopularPlaylists():Single<ListOfPlaylists>



    //tracks
    @POST("get_track")
    fun getTrackById(@Body id: Int): Single<Track>

    @POST("post_track")
    fun postTrack(@Body track: Track): Single<Boolean>

    //news
    @GET("news")
    fun getAllNews():Single<List<News>>

    //utils
    @GET("get_networks")
    fun getAllAvailableNetworks():Single<List<Network>>

}