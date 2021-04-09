package repositories.implementations

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import models.core.Account
import models.wrappers.AccountInfo
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



    override fun registerUser(): Single<Int> {
        TODO("Not yet implemented")
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