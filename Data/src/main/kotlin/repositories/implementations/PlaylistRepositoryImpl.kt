package repositories.implementations

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import models.core.music.Playlist
import models.core.music.Track
import models.utils.PlaylistImage
import models.wrappers.music.LinkPlaylist
import models.wrappers.music.ListOfPlaylists
import network.api.ApiService
import repositories.PlaylistRepository
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

class PlaylistRepositoryImpl @Inject constructor(
    private val api: ApiService
) : PlaylistRepository {

    private var userPlaylists: List<Playlist> = emptyList()
    private var popularPlaylists: List<Playlist> = emptyList()

    override fun getPlaylists(userId: Int): Single<List<Playlist>> {
       return api.getUsersPlaylists(userId)
            .subscribeOn(Schedulers.io())
            .map {
                getPlaylists(it,userId)
            }
    }

    override fun getPopularPlaylists(): Single<List<Playlist>> {
        return api.getPopularPlaylists()
            .subscribeOn(Schedulers.io())
            .map {
                getPlaylists(it,0)
            }
    }

    fun getPlaylists(listOfLinkPlaylists:ListOfPlaylists, accountId:Int = 0):List<Playlist>{
        val allPlaylists = ArrayList<Playlist>()
        listOfLinkPlaylists.list.forEach { linkPlaylist ->


            val currentPlaylist = Playlist(
                linkPlaylist.id, ArrayList(),
                "${linkPlaylist.id}", PlaylistImage()
            )

            linkPlaylist
                .trackIdList
                .forEach { trackId ->

                    val path = LocalStorageAccessor
                        .getPathTrack(accountId, trackId)

                    if (!File(path).exists()) {
                        val fos = FileOutputStream(path)
                        val byteArray = LocalStorageAccessor
                            .decodeByteArrayFromBody(api.getTrackById(trackId).execute())
                        fos.write(byteArray)
                    }

                    currentPlaylist.addTrack(
                        LocalStorageAccessor.getTrackById(accountId, trackId)
                    )
                }

            allPlaylists.add(currentPlaylist)

            val playlistPath = LocalStorageAccessor.getPathPlaylist(
                accountId,
                linkPlaylist.id
            )

            if (!File(playlistPath).exists()) {
                LocalStorageAccessor.savePlaylist(accountId, linkPlaylist)
            }
        }
        return allPlaylists
    }


    override fun updatePlaylist(playlist: Playlist): Single<Boolean> {
        return api.updatePlaylist(
            LinkPlaylist(playlist.id,
                playlist.getAllTracks().map { it.id })
        )
            .map { true }
    }

    private fun checkFilter(playlist: Playlist, text: String): Boolean {
        return playlist.name.toLowerCase().contains(text.toLowerCase())
    }

    override fun filterPlaylists(text: String): Single<List<Playlist>> {
        return Single.just(popularPlaylists.filter { checkFilter(it, text) }
                + userPlaylists.filter { checkFilter(it, text) })
    }
}