package network.api
import io.reactivex.rxjava3.core.Single
import models.core.Account
import models.core.Playlist
import models.core.Track
import models.wrappers.account.AccountListWrapper
import models.wrappers.account.AccountRegistration
import models.wrappers.playlist.ListOfPlaylists
import models.wrappers.account.RegistrationResult
import models.wrappers.account.UserInfo
import models.wrappers.playlist.PlaylistUpdateResult
import retrofit2.http.*


interface ApiService {

    //users

    @GET("get_all_users")
    fun getAllUsers(): Single<AccountListWrapper>

    @POST("register_user")
    fun registerUser(@Body accountRegistration: AccountRegistration): Single<RegistrationResult>

    @GET("get_user")
    fun getUserById(@Body id: Int):Single<UserInfo>

    @GET("get_user")
    fun getUserByNick(@Body nickname: String):Single<UserInfo>

    //playlists
    @GET("get_user_playlists")
    fun getUsersPlaylists():Single<ListOfPlaylists>

    @GET("get_playlists")
    fun getAllPlayLists():Single<ListOfPlaylists>

    @POST()
    fun updatePlaylist(@Body playlist: Playlist): Single<PlaylistUpdateResult>

    //tracks
    @POST("get_track")
    fun getTrackById(@Body id: Int): Single<Track>

    @POST("post_track")
    fun postTrack(@Body track: Track): Single<Boolean>

}