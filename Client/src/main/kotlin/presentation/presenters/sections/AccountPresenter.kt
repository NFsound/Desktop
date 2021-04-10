package presentation.presenters.sections

import application.SonusApplication
import interactors.AccountInteractor
import io.reactivex.rxjava3.schedulers.Schedulers
import javafx.application.Platform
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

    override fun filter(text: String) {
        viewState.filterView(text)
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

    fun onInitialLoad(){

    }

}