package repositories.implementations

import io.reactivex.rxjava3.core.Single
import models.core.music.Playlist
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
            .map {it->
                getPlaylists(it,userId)
            }
    }

    override fun getPopularPlaylists(): Single<List<Playlist>> {
        return api.getPopularPlaylists()
            .map {
                getPlaylists(it,0)
            }
    }

    private fun getPlaylists(listOfLinkPlaylists:ListOfPlaylists, accountId:Int = 0):List<Playlist>{
        val allPlaylists = ArrayList<Playlist>()
        listOfLinkPlaylists.list.forEach { linkPlaylist ->


            val currentPlaylist = Playlist(
                 ArrayList(),
                linkPlaylist.name, PlaylistImage()
            )

            linkPlaylist
                .trackIdList
                .forEach { trackId ->

                    val path = LocalStorageAccessor
                        .getPathTrack(accountId, trackId)

                    if (!File(path).exists()) {
                        val fos = FileOutputStream(path)
                        val byteArray = LocalStorageAccessor
                            .decodeByteArrayFromBody(api.getTrackByIdPub(trackId).execute())
                        fos.write(byteArray)
                    }

                    currentPlaylist.addTrack(
                        LocalStorageAccessor.getTrackById(accountId, trackId)
                    )
                }

            allPlaylists.add(currentPlaylist)

            val playlistPath = LocalStorageAccessor.getPathPlaylist(
                accountId,
                linkPlaylist.name
            )

            if (!File(playlistPath).exists()) {
                LocalStorageAccessor.savePlaylist(accountId, linkPlaylist)
            }
        }
        return allPlaylists
    }


    override fun updatePlaylist(accountId:Int, playlist: Playlist): Single<Boolean> {
        val linkPlaylist = LinkPlaylist(
            playlist.getAllTracks().map { it.id }, playlist.name)
        return api.updatePlaylist(
            linkPlaylist
        ).doAfterSuccess {
            LocalStorageAccessor.savePlaylist(accountId, linkPlaylist)
        }.map { true }
    }

    private fun checkFilter(playlist: Playlist, text: String): Boolean {
        return playlist.name.toLowerCase().contains(text.toLowerCase())
    }

    override fun filterPlaylists(text: String): Single<List<Playlist>> {
        return Single.just(popularPlaylists.filter { checkFilter(it, text) }
                + userPlaylists.filter { checkFilter(it, text) })
    }
}