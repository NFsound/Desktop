package repositories

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import models.core.account.*
import javax.inject.Singleton

@Singleton
interface AccountRepository {

    fun registerUser(accountRegistration: AccountRegistration): Single<RegistrationResult>

    fun login(accountLogin: AccountLogin): Single<LoginResult>

    fun getCurrentUser():Single<Account>

    fun logOut():Completable
}