package repositories.implementations

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.AsyncSubject
import models.core.music.Track
import models.core.networks.GenerationParams
import models.wrappers.music.GenerationBody
import network.api.ApiService
import okhttp3.ResponseBody
import repositories.TrackRepository
import repositories.implementations.LocalStorageAccessor.decodeByteArrayFromBody
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject


class TrackRepositoryImpl @Inject constructor(
    private val api: ApiService
) : TrackRepository {



    override fun getAllTracks(accountId: Int): Single<List<Track>> {
        api.getAllTracksByUserId(accountId)
            .subscribeOn(Schedulers.io())
            .subscribe { idlist ->
                for (id in idlist.list) {
                    val path = LocalStorageAccessor.getPathTrack(accountId, id)
                    if (!File(path).exists()) {
                        val fos = FileOutputStream(path)
                        val byteArray = decodeByteArrayFromBody(api.getTrackById(id).execute())
                        fos.write(byteArray)
                    }
                }
            }

        val allList = LocalStorageAccessor.getAllTracksByAccountId(accountId)
        return Single.just(allList)
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
            .subscribeOn(Schedulers.io())
            .doOnSuccess { it ->
                val trackId: Int = it.trackId
                var currentCode = 404
                var result: Response<ResponseBody> =
                    api.getTrackById(trackId)
                        .execute()
                while (currentCode == 404) {
                    result = api.getTrackById(trackId)
                        .execute()
                    currentCode = result.code()
                }
                LocalStorageAccessor.saveTrack(
                    accountId, trackId,
                    decodeByteArrayFromBody(result)
                )
            }.map { it ->
                LocalStorageAccessor.getTrackById(accountId, it.trackId)
            }
    }
}
