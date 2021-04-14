package repositories.implementations

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import models.core.account.*
import models.wrappers.account.LoginBody
import network.api.ApiService
import repositories.AccountRepository
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val api: ApiService
) : AccountRepository {

    private var currentAccount: Account

    init {
        currentAccount = Account(-1,"Demo","demo","qwerty")
        //TODO try to get account from local storage
    }


    override fun registerUser(accountRegistration: AccountRegistration): Single<RegistrationResult> {
        return api.registerUser(accountRegistration)
    }

    override fun login(accountLogin: AccountLogin): Single<LoginResult> {
        return api.loginUser(accountLogin).doOnSuccess {
            api.getUserById(it.id).map {
                userInfo -> Account(it.id,
                    userInfo.nickname,
                    userInfo.login,
                    accountLogin.password)
            }.subscribe {
                res->
                currentAccount = res
            }
        }
    }

    override fun getCurrentUser(): Single<Account> {
        return Single.just(currentAccount)
    }

    override fun logOut(): Completable {
        return Completable.fromAction {
            currentAccount = Account(-1,"Demo","demo","qwerty")
        }
    }

}