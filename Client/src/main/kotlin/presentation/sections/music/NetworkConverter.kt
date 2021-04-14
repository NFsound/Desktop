package presentation.sections.music

import javafx.util.StringConverter
import models.core.networks.Network

class NetworkConverter: StringConverter<Network>() {
    override fun toString(network: Network?): String {
        if (network != null) {
            return "Author: ${network.author}, type:${network.type}, name:${network.name}"
        }else{
            return "Choose network"
        }
    }

    override fun fromString(string: String?): Network {
        return Network("Author",1,"Name","Type")
    }
}