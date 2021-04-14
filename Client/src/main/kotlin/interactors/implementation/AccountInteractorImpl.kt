package interactors.implementation

import interactors.AccountInteractor
import io.reactivex.rxjava3.core.Single
import models.core.Account
import models.core.AccountRegistration
import repositories.AccountRepository
import javax.inject.Inject

class AccountInteractorImpl @Inject constructor(
    private val accountRepository: AccountRepository
) : AccountInteractor {

    override fun registerAccount(nickName: String,
                                 login:String,
                                 password:String)
    : Single<Boolean> {
        accountRepository.registerUser()
    }

    override fun loginAccount(): Single<Boolean> {

    }

    override fun getAllUsers(): Single<List<Account>> {

    }

    override fun getCurrentAccount(): Single<Account> {

    }

    override fun logOut(): Single<Boolean> {

    }

    init {

    }

}