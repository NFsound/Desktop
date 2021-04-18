package interactors.implementation

import interactors.AccountInteractor
import io.reactivex.rxjava3.core.Single
import models.core.account.*
import repositories.AccountRepository
import javax.inject.Inject

class AccountInteractorImpl @Inject constructor(
    private val accountRepository: AccountRepository
) : AccountInteractor {

    override fun registerAccount(
        nickName: String,
        login: String,
        password: String
    ) : Single<RegistrationResult> {
        return accountRepository
            .registerUser(AccountRegistration(nickName, login, password))
    }

    override fun loginAccount(nickName: String, password: String): Single<LoginResult> {
        return accountRepository
            .login(AccountLogin(nickName, password))
    }

    override fun getCurrentAccount(): Single<Account> {
        return accountRepository
            .getCurrentUser()
    }

    override fun logOut(): Single<Boolean> {
        return accountRepository.logOut()
            .andThen(Single.just(true))
            .onErrorResumeWith {
                Single.just(false)
            }
    }

}