package presentation.presenters.sections

import application.SonusApplication
import interactors.AccountInteractor
import javafx.application.Platform
import models.core.account.Account
import presentation.presenters.main.CenterPresenter
import presentation.sections.account.AccountView
import javax.inject.Inject

class AccountPresenter() : SectionPresenter {

    @Inject
    lateinit var viewState: AccountView

    @Inject
    lateinit var accountInteractor: AccountInteractor

    init {
        SonusApplication.getInstance().applicationComponent.inject(this)
    }

    override lateinit var centerPresenter: CenterPresenter


    override fun onInitialLoad() {
        accountInteractor.getCurrentAccount().onErrorResumeWith {
            Platform.runLater {
                viewState.showErrorMessage("Couldn't login. No connection with server")
            }
        }.subscribe { account ->
            Platform.runLater {
                viewState.initializeUserInfo(account)
                viewState.showAccountUI()
            }
        }
    }

    fun logOut() {
        accountInteractor.logOut().onErrorResumeWith {

        }.subscribe { result ->
            if (result) {
                Platform.runLater {
                    viewState.hideAccountUI()
                }
            }
        }
    }

    fun registerAccount(nick: String, login: String, password: String) {
        accountInteractor.registerAccount(nick, login, password).onErrorResumeWith {
            Platform.runLater {
                viewState.showErrorMessage("Couldn't login. No connection with server")
            }
        }.subscribe { result ->
            Platform.runLater {
                if (result.status) {
                    viewState.initializeUserInfo(Account(result.id, nick, login, password))
                    viewState.showAccountUI()
                } else {
                    viewState.showErrorMessage(result.message)
                }
            }
        }
    }

    fun logIn(nick: String, login: String, password: String) {
        accountInteractor.loginAccount(nick, password)
            .subscribe { account ->
                Platform.runLater {
                    viewState.initializeUserInfo(Account(100, nick, login, password))
                    viewState.showAccountUI()
                }
            }
    }
}