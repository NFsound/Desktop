package interactors.implementation

import interactors.AccountInteractor
import io.reactivex.rxjava3.core.Single
import models.core.Account
import models.core.AccountRegistration
import repositories.AccountRepository
import javax.inject.Inject

class AccountInteractorImpl @Inject constructor(
    private val accountRepository: AccountRepository
) : AccountInteractor {

    private lateinit var currentAccount: Account

    override fun registerAccount(nickName: String, login:String, password:String)
    : Single<Boolean> {
        val ar = AccountRegistration(nickName, login, password)
        return accountRepository.registerUser(ar).doAfterSuccess {
            currentAccount = Account(it, ar.nickname, ar.login, ar.password)
        }.map{
            it != -1
        }
    }

    override fun loginAccount(): Single<Boolean> {
        return accountRepository.login(currentAccount).map{
            it != -1
        }
    }

    override fun getAllUsers(): Single<List<Account>> {
        return accountRepository.getAllUsers()
    }

    override fun getCurrentAccount(): Single<Account> {
        return Single.just(currentAccount)
    }

    override fun logOut(): Single<Boolean> {
        currentAccount = Account(-1,"","","")
        return Single.just(true)
    }

    init {
        currentAccount = Account(3412,"Somebody",
            "somebody@gmail.com", "Strong_password")

    }

}