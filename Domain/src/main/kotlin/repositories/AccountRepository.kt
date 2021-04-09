package repositories

import io.reactivex.rxjava3.core.Single
import models.core.Account
import models.wrappers.AccountInfo

interface AccountRepository {

    fun getUser(Id: Int): Single<Account>

    fun getUser(nickName: String): Single<Account>

    fun registerUser(): Single<Int>

    fun login(account: Account): Single<AccountInfo>

    fun getAllUsers():Single<List<Account>>
}