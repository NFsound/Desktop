package network.api
import io.reactivex.rxjava3.core.Single
import models.wrappers.AccountListWrapper
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.http.*


interface ApiService {

    @GET("")
    fun getUser(@Header("id") id:Int,
                @Header(value = "Authorization") apiKey: String ="")

    @GET("")
    fun getUser(@Body nick:String)

    @GET("get_all_users")
    fun getAllUsers(): Single<AccountListWrapper>

    @GET
    fun getTrack()

    @GET()
    fun getAllTracks()



}