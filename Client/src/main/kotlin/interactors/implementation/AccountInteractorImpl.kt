package interactors.implementation

import interactors.AccountInteractor
import io.reactivex.rxjava3.core.Single
import models.core.Account
import models.wrappers.account.AccountRegistration
import models.wrappers.account.RegistrationResult
import repositories.AccountRepository
import javax.inject.Inject

class AccountInteractorImpl @Inject constructor(
    private val accountRepository: AccountRepository
) : AccountInteractor {
    override fun registerAccount(nickName: String, login:String, password:String)
    : Single<RegistrationResult> {
        return accountRepository.registerUser(AccountRegistration(nickName, login, password))
    }

    override fun loginAccount(): Single<Boolean> {
        TODO("Not yet implemented")
    }

    override fun getAllUsers(): Single<List<Account>> {
        return accountRepository.getAllUsers()
    }


}