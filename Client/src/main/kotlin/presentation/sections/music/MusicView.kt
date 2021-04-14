package presentation.sections.music

import models.core.networks.Network
import presentation.sections.common.SectionView

interface MusicView: SectionView {

    fun loadNetworks(networks: List<Network>)



}