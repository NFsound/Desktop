package repositories

import io.reactivex.rxjava3.core.Single
import models.core.Account
import models.wrappers.AccountInfo
import models.wrappers.AccountRegistration
import models.wrappers.RegistrationResult

interface AccountRepository {

    fun getUser(Id: Int): Single<Account>

    fun getUser(nickName: String): Single<Account>

    fun registerUser(accountRegistration: AccountRegistration): Single<Boolean>

    fun login(account: Account): Single<AccountInfo>

    fun getAllUsers():Single<List<Account>>
}