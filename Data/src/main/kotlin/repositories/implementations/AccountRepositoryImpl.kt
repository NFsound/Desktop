package repositories.implementations

import io.reactivex.rxjava3.core.Single
import models.core.Account
import models.core.AccountRegistration
import models.core.UserInfo
import models.wrappers.account.LoginBody
import network.api.ApiService
import repositories.AccountRepository
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(private val api:ApiService): AccountRepository {

    override fun getUser(id: Int): Single<UserInfo> {
        return api.getUserById(id)
    }

    override fun getUser(nickName: String): Single<UserInfo> {
        return api.getUserByNick(nickName)
    }



    override fun registerUser(accountRegistration: AccountRegistration): Single<Int> {
        return api.registerUser(accountRegistration).map {
            it-> 0
        }
    }

    override fun login(account: Account): Single<Int> {
        val loginBody = LoginBody()
        return api.loginUser(loginBody)
    }

    override fun getAllUsers(): Single<List<Account>> {
        return api.getAllUsers().map {
                it.list
            }
    }
}