package interactors

import io.reactivex.rxjava3.core.Single

interface AccountInteractor {

    fun registerAccount(): Single<Boolean>

    fun loginAccount(): Single<Boolean>
    
}