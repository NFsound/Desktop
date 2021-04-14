package repositories

import io.reactivex.rxjava3.core.Single
import models.core.Account
import models.core.AccountRegistration
import models.core.UserInfo


interface AccountRepository {

    fun getUser(Id: Int): Single<Account>

    fun registerUser(accountRegistration: AccountRegistration): Single<Boolean>

    fun login(account: Account): Single<Boolean>

    fun getCurrentUser():Single<Account>
}