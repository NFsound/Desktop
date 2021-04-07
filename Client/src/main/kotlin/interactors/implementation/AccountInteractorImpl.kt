package interactors.implementation

import interactors.AccountInteractor
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class AccountInteractorImpl @Inject constructor(): AccountInteractor {
    override fun registerAccount(): Single<Boolean> {
        TODO("Not yet implemented")
    }

    override fun loginAccount(): Single<Boolean> {
        TODO("Not yet implemented")
    }
}