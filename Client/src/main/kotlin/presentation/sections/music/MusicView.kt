package presentation.sections.music

import models.core.music.Playlist
import models.core.music.Track
import models.core.networks.Network
import presentation.sections.common.SectionView
import tornadofx.SVGIcon

interface MusicView: SectionView {

    fun loadNetworks(networks: List<Network>)

    fun addGeneratedTrack(track:Track)

    fun openPlaylistsView(icon:SVGIcon, playlists: List<Playlist>, track: Track)
}