package models.core.music

class Track(
    val id: Int,
    val name: String,
    val userId: Int,
    val resPath: String,
    val authorName: String
) {
    companion object {
        private val emptyTrack = Track(-1, "", 0, "", "")
        fun emptyTrack(): Track {
            return emptyTrack
        }
    }

    override fun toString(): String {
        return resPath
    }
}
