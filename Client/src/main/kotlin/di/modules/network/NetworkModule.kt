package di.modules.network

import application.Config
import dagger.Provides
import dagger.Module
import network.api.ApiService
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    @Provides
    fun provideApiService():ApiService{
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client: OkHttpClient = OkHttpClient.Builder()
                .apply {
                    this.connectionPool(ConnectionPool(0, 1, TimeUnit.NANOSECONDS))
                    .protocols(listOf(Protocol.HTTP_1_1))
                    connectTimeout(100, TimeUnit.SECONDS)
                    writeTimeout(100, TimeUnit.SECONDS)
                    readTimeout(100, TimeUnit.SECONDS)
                    retryOnConnectionFailure(false)
                }
                //.addInterceptor(interceptor).build()
                .build()

            val retrofit = Retrofit
                .Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(client)
                .baseUrl(Config.BASE_URL)
                .build()
            return retrofit.create(ApiService::class.java)
    }
}