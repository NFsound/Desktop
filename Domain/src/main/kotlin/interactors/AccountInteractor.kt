package interactors

import io.reactivex.rxjava3.core.Single
import models.core.account.Account
import models.core.account.AccountRegistration
import models.core.account.LoginResult
import models.core.account.RegistrationResult

interface AccountInteractor {

    fun registerAccount(nickName: String, login:String, password:String):
            Single<RegistrationResult>

    fun loginAccount(nickName: String, login: String, password:String): Single<LoginResult>

    fun getCurrentAccount(): Single<Account>

    fun logOut(): Single<Boolean>
}