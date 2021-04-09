package presentation.sections.account

import javafx.scene.Parent
import models.core.Account
import presentation.presenters.sections.AccountPresenter
import presentation.presenters.sections.SectionPresenter
import tornadofx.*
import javax.inject.Inject

class AccountViewImpl: View(), AccountView {

    override fun printAllUsers(list:List<Account>) {
        for (user in list){
            root.add(button{
                text = user.nickname
            })
        }
    }

    override var sectionTitle = "Account"


    override fun filterView(text: String) {
        println("from account $text")
    }

    override fun setPresenter(presenter: SectionPresenter) {
        accountPresenter = presenter as AccountPresenter
    }

    override fun getPresenter(): SectionPresenter {
        TODO("Not yet implemented")
    }


    private lateinit var accountPresenter: AccountPresenter



    override val root: Parent = vbox {
        button("account"){
            setOnMouseClicked {
                accountPresenter.onButtonClicked()
            }
        }

    }
}