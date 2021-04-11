package repositories.implementations

import io.reactivex.rxjava3.core.Single
import models.core.GenerationParams
import models.core.Network
import network.api.ApiService
import repositories.UtilsRepository
import javax.inject.Inject

class UtilsRepositoryImpl @Inject constructor(private val api: ApiService): UtilsRepository {
    override fun getAllAvailableNetworks(): Single<List<Network>> {
        return api.getAllAvailableNetworks()
    }
}