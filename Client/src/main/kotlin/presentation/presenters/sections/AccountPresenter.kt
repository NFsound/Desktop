package presentation.presenters.sections

import application.SonusApplication
import interactors.AccountInteractor
import javafx.application.Platform
import models.core.account.Account
import models.core.account.LoginResult
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
        accountInteractor.getCurrentAccount()
            .onErrorResumeWith {
                Platform.runLater {
                    viewState.showErrorMessage("Couldn't restore account info from memory")
                    viewState.hideAccountUI()
                }
            }.subscribe { account ->
                accountInteractor
                    .loginAccount(account.nickname, account.login, account.password)
                    .onErrorResumeWith {
                        Platform.runLater {
                            viewState.showErrorMessage("Couldn't login. No connection with server")
                            viewState.hideAccountUI()
                        }
                    }
                    .subscribe { loginResult ->
                        if (loginResult.status) {
                            Platform.runLater {
                                viewState.initializeUserInfo(account)
                                viewState.showAccountUI()
                            }
                        }
                    }
            }
    }

    fun logOut() {
        accountInteractor.logOut().onErrorResumeWith {
            Platform.runLater {
                viewState.showErrorMessage("Couldn't log out. No connection with server")
            }
        }.subscribe { result ->
            Platform.runLater {
                if (result) {
                    viewState.hideAccountUI()
                } else {
                    viewState.showErrorMessage("Couldn't log out. Some error happened")
                }
            }
        }
    }

    fun registerAccount(nick: String, login: String, password: String) {
        accountInteractor.registerAccount(nick, login, password).onErrorResumeWith {
            Platform.runLater {
                viewState.showErrorMessage("Couldn't register. No connection with server")
            }
        }.subscribe { result ->
            Platform.runLater {
                if (result.status) {
                    viewState.showAccountUI()
                    viewState.initializeUserInfo(Account(result.id, nick, login, password))
                } else {
                    viewState.showErrorMessage(result.message)
                }
            }
        }
    }

    fun logIn(nick: String, login: String, password: String) {
        Platform.runLater {
            accountInteractor.loginAccount(nick, login, password)
                .onErrorResumeWith {
                    Platform.runLater {
                        viewState.showErrorMessage("Couldn't login")
                    }
                }
                .subscribe { loginResult ->
                    Platform.runLater {
                        if (loginResult.status) {
                            viewState.initializeUserInfo(Account(loginResult.id, nick, login, password))
                            viewState.showAccountUI()
                        } else {
                            viewState.showErrorMessage(loginResult.message)
                        }
                    }
                }
        }
    }

    fun changePassword(newPassword: String) {
        accountInteractor.getCurrentAccount().subscribe { it ->
            it.password = newPassword
            Platform.runLater {
                viewState.showErrorMessage("Couldn't login. No connection with server")
            }
        }
    }

}