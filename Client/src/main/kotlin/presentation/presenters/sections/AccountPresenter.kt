package presentation.presenters.sections

import application.SonusApplication
import interactors.AccountInteractor
import javafx.application.Platform
import models.core.account.Account
import presentation.presenters.main.CenterPresenter
import presentation.sections.account.AccountView
import javax.inject.Inject

class AccountPresenter():SectionPresenter {

    @Inject
    lateinit var viewState: AccountView

    @Inject
    lateinit var accountInteractor: AccountInteractor



    init {
        SonusApplication.getInstance().applicationComponent.inject(this)
    }

    override lateinit var centerPresenter: CenterPresenter

    override fun filter(text: String) {

    }

    fun onButtonClicked(){
        accountInteractor.registerAccount("aero",
            "aero","aeroooooo")
            .subscribe{
                list->
            Platform.runLater {
                //viewState.printAllUsers(list)
            }
        }
/*        accountInteractor.getAllUsers()
            .subscribe{
                list->
                Platform.runLater {
                    viewState.printAllUsers(list)
                }
            }*/
    }

    override fun onInitialLoad() {
        accountInteractor.getCurrentAccount().subscribe { account ->
            Platform.runLater {
                viewState.initializeUserInfo(account)
                viewState.showAccountUI()
            }
        }
    }

    fun logOut(){
        accountInteractor.logOut().subscribe {
            it->viewState.hideAccountUI()
        }
    }

    fun registerAccount(nick:String, login:String, password: String){
        accountInteractor.registerAccount(nick, login, password)
    }

    fun logIn(nick:String, login:String, password: String){
        accountInteractor.loginAccount(nick,password).subscribe { account ->
            Platform.runLater {
                viewState.initializeUserInfo(Account(100,nick,login,password))
                viewState.showAccountUI()
            }
        }
    }
}