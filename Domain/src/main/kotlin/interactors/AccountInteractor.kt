package interactors

import io.reactivex.rxjava3.core.Single
import models.core.Account

interface AccountInteractor {

    fun registerAccount(nickName: String, login:String, password:String):
            Single<Boolean>

    fun loginAccount(): Single<Boolean>

    fun getAllUsers(): Single<List<Account>>

    fun getCurrentAccount(): Single<Account>

    fun logOut(): Single<Boolean>
}