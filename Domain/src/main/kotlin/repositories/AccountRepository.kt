package repositories

import io.reactivex.rxjava3.core.Single
import models.core.Account
import models.core.AccountRegistration
import models.core.UserInfo


interface AccountRepository {

    fun getUser(Id: Int): Single<UserInfo>

    fun getUser(nickName: String): Single<UserInfo>

    fun registerUser(accountRegistration: AccountRegistration): Single<Int>

    fun login(account: Account): Single<Int>

    fun getAllUsers():Single<List<Account>>
}