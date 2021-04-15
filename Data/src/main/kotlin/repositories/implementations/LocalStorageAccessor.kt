package repositories.implementations

import models.core.account.Account
import models.core.music.Track
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

object LocalStorageAccessor {

    val localStoragePath: String = ""

    fun storeAccount(account: Account) {

    }

    fun restoreAccount(): Account {
        return Account(-1, "Demo", "demo", "qwerty")
    }

    fun getPathTrack(accountId: Int, trackId: Int) = "account${accountId}/tracks/${trackId}.wav"

    fun getAuthorName(accountId: Int) = "account${accountId}"

    fun getTrackById(accountId: Int, trackId: Int): Track {
        return Track(
            trackId, "id${trackId}", accountId,
            getPathTrack(accountId, trackId), "account${accountId}"
        )
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
        File(getPathTrack(accountId, trackId)).writeBytes(byteArray)
    }


}