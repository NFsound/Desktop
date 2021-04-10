package repositories.implementations

import io.reactivex.rxjava3.core.Single
import models.core.GenerationParams
import network.api.ApiService
import repositories.UtilsRepository
import javax.inject.Inject

class UtilsRepositoryImpl @Inject constructor(private val api: ApiService): UtilsRepository {
    override fun getGenerationParams(): Single<List<GenerationParams>> {
        TODO("Not yet implemented")
    }
}