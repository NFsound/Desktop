package repositories

import io.reactivex.rxjava3.core.Single
import models.core.GenerationParams

interface UtilsRepository {

    fun getGenerationParams(): Single<List<GenerationParams>>

}