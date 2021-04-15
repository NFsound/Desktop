package presentation.sections.music

import models.core.music.Track
import models.core.networks.Network
import presentation.sections.common.SectionView

interface MusicView: SectionView {

    fun loadNetworks(networks: List<Network>)

    fun addGeneratedTrack(track:Track)

}