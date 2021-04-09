package interactors.implementation

import interactors.AccountInteractor
import io.reactivex.rxjava3.core.Single
import models.core.Account
import repositories.AccountRepository
import javax.inject.Inject

class AccountInteractorImpl @Inject constructor(
    private val accountRepository: AccountRepository
) : AccountInteractor {
    override fun registerAccount(): Single<Boolean> {
        TODO("Not yet implemented")
    }

    override fun loginAccount(): Single<Boolean> {
        TODO("Not yet implemented")
    }

    override fun getAllUsers(): Single<List<Account>> {
        return accountRepository.getAllUsers()
    }


}