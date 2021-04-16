package repositories.implementations

import io.reactivex.rxjava3.core.Single
import models.core.music.Track
import models.core.networks.GenerationParams
import models.wrappers.music.GenerationBody
import network.api.ApiService
import okhttp3.ResponseBody
import repositories.TrackRepository
import repositories.implementations.LocalStorageAccessor.decodeByteArrayFromBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject


class TrackRepositoryImpl @Inject constructor(
    private val api: ApiService
) : TrackRepository {


    override fun getAllTracks(accountId: Int): Single<List<Track>> {
        return api.getAllTracksByUserId(accountId)
            .map {
                    idlist ->
                    for (id in idlist.tracks) {
                        val path = LocalStorageAccessor.getPathTrack(accountId, id)
                        if (!File(path).exists()) {
                            val fos = FileOutputStream(path)
                            val byteArray = decodeByteArrayFromBody(
                                //api.getTrackByIdGen(id).execute()
                                api.getTrackByIdPub(id).execute()
                            )
                            fos.write(byteArray)
                        }
                    }
                    return@map LocalStorageAccessor.getAllTracksByAccountId(accountId)
            }
    }

    override fun generateTrack(
        accountId: Int,
        generationParams: GenerationParams,
        byteArray: ByteArray
    ): Single<Track> {

        return api.sendGenerationRequest(
            GenerationBody(
                byteArray,
                generationParams
            )
        )
            .doOnSuccess { it ->
                val trackId: Int = it.trackId
                var currentCode = 404
                var call: Call<ResponseBody> =
                    api.getTrackByIdGen(trackId)
                call.enqueue(object : Callback<ResponseBody?> {
                    override fun onResponse(
                        call: Call<ResponseBody?>,
                        response: Response<ResponseBody?>
                    ) {
                        LocalStorageAccessor.saveTrack(
                            accountId, trackId,
                            decodeByteArrayFromBody(response)
                        )
                    }

                    override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                        TODO("Not yet implemented")
                    }
                }

                )

            }.map { it ->
                LocalStorageAccessor.getTrackById(accountId, it.trackId)
            }
    }
}
