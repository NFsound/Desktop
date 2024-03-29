package repositories.implementations

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import models.core.account.*
import models.wrappers.account.IdBody
import network.api.ApiService
import repositories.AccountRepository
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val api: ApiService
) : AccountRepository {

    private var currentAccount: Account

    init {
        currentAccount = LocalStorageAccessor.restoreAccount()
    }


    override fun registerUser(accountRegistration: AccountRegistration): Single<RegistrationResult> {
        return api.registerUser(accountRegistration)
    }

    override fun login(accountLogin: AccountLogin): Single<LoginResult> {
        return api.loginUser(accountLogin)
            .doAfterSuccess {
                api.getUserById(IdBody(it.id))
                    .map { userInfo ->
                        Account(
                            it.id,
                            accountLogin.nickname,
                            accountLogin.login,
                            accountLogin.password
                        )
                    }
                    .subscribe { res ->
                        currentAccount = res
                        LocalStorageAccessor.storeAccount(currentAccount)
                    }
            }
    }

    override fun getCurrentUser(): Single<Account> {
        return Single.just(currentAccount)
    }

    override fun logOut(): Completable {
        return Completable.fromAction {
            currentAccount = Account(0, "Demo", "demo", "qwerty")
        }
    }

}