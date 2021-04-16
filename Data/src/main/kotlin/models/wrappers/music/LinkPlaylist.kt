package models.wrappers.music

import com.google.gson.annotations.SerializedName

class LinkPlaylist(val id:Int,
                   @SerializedName("songs") val trackIdList:List<Int>,
                   val name: String
                   ) {
}