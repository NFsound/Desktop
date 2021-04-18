package models.wrappers.music

import com.google.gson.annotations.SerializedName

class LinkPlaylist(
                   @SerializedName("songs") val trackIdList:List<Int>,
                   @SerializedName("name") val name: String
                   ) {
}