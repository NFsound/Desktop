package repositories.implementations

import io.reactivex.rxjava3.core.Single
import models.core.Account
import models.wrappers.account.AccountInfo
import models.wrappers.account.AccountRegistration
import network.api.ApiService
import repositories.AccountRepository
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(private val api:ApiService): AccountRepository {
    override fun getUser(Id: Int): Single<Account> {
        TODO("Not yet implemented")
    }

    override fun getUser(nickName: String): Single<Account> {
        TODO("Not yet implemented")
    }



    override fun registerUser(accountRegistration: AccountRegistration): Single<Boolean> {
        return api.registerUser(accountRegistration).map {
            it.status.contentEquals("Ok!")
        }
    }

    override fun login(account: Account): Single<AccountInfo> {
        TODO("Not yet implemented")
    }

    override fun getAllUsers(): Single<List<Account>> {
        return api.getAllUsers().map {
                it.list
            }
    }
}