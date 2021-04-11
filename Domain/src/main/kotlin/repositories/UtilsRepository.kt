package repositories

import io.reactivex.rxjava3.core.Single
import models.core.GenerationParams
import models.core.Network

interface UtilsRepository {

    fun getAllAvailableNetworks(): Single<List<Network>>

}