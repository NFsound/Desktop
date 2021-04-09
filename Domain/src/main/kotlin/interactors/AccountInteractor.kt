package interactors

import io.reactivex.rxjava3.core.Single
import models.core.Account

interface AccountInteractor {

    fun registerAccount(): Single<Boolean>

    fun loginAccount(): Single<Boolean>

    fun getAllUsers(): Single<List<Account>>

}