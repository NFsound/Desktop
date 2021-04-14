package repositories

import io.reactivex.rxjava3.core.Single
import models.core.networks.Network

interface UtilsRepository {

    fun getAllAvailableNetworks(): Single<List<Network>>

}