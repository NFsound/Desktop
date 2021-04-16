package repositories.implementations

import models.core.account.Account
import models.core.music.Track
import models.wrappers.music.LinkPlaylist
import okhttp3.ResponseBody
import retrofit2.Response
import java.io.File

object LocalStorageAccessor {

    val localStoragePath: String = ""

    fun storeAccount(account: Account) {

    }


    fun decodeByteArrayFromBody(
        responce: Response<ResponseBody?>
    ): ByteArray {
        return responce.body()!!.bytes()
    }

    fun restoreAccount(): Account {
        return Account(0, "Demo", "demo", "qwerty")
    }

    fun getPathTrack(accountId: Int, trackId: Int) = "account${accountId}/tracks/${trackId}.wav"

    fun getPath(accountId: Int) = "account${accountId}/tracks"

    fun getPathPlaylist(accountId: Int) = "account${accountId}/playlists"

    fun getAuthorName(accountId: Int) = "account${accountId}"

    fun getPathPlaylist(accountId: Int, playlistName: String) = "account${accountId}/playlists/${playlistName}.txt"

    fun getTrackById(accountId: Int, trackId: Int): Track {
        return Track(
            trackId, "id${trackId}", accountId,
            getPathTrack(accountId, trackId), "account${accountId}"
        )
    }

    fun savePlaylist(accountId: Int, linkPlaylist: LinkPlaylist) {
        val str = linkPlaylist.trackIdList.map { it ->
            "${it}\n"
        }.reduce { acc, string -> acc + string }
        File(getPathPlaylist(accountId)).mkdirs()
        File(getPathPlaylist(accountId, linkPlaylist.name))
            .writeText(str)
    }

    fun getAllTracksByAccountId(accountId: Int): List<Track> {
        val list: ArrayList<Track> = ArrayList()
        val folder = File("account${accountId}/tracks")
        val listOfFiles = folder.listFiles()
        for (file in listOfFiles) {
            val curId = file.nameWithoutExtension.toInt()
            list.add(
                Track(
                    curId, "id${curId}", accountId,
                    getPathTrack(accountId, curId),
                    getAuthorName(accountId)
                )
            )
        }
        return list
    }

    fun saveTrack(accountId: Int, trackId: Int, byteArray: ByteArray) {
        val file = File(getPath(accountId))
        file.mkdirs()
        val file2 = File(getPathTrack(accountId, trackId))
        file2.createNewFile()
        file2.writeBytes(byteArray)
    }


}