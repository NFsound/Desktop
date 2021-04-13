package presentation.sections.home

import models.core.Playlist
import presentation.sections.common.SectionView

interface HomeView: SectionView {

    fun renderPopularPlaylists(listOfPlaylist: List<Playlist>)

    fun renderAccountPlaylists(listOfPlaylist: List<Playlist>)



}