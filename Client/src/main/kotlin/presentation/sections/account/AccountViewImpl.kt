package presentation.sections.account

import javafx.scene.Parent
import presentation.presenters.sections.AccountPresenter
import presentation.presenters.sections.SectionPresenter
import tornadofx.*
import javax.inject.Inject

class AccountViewImpl: View(), AccountView {

    override var sectionTitle = "Account"

    override fun providePresenter(): SectionPresenter {
        if (accountPresenter == null){
            return AccountPresenter()
        }
        return accountPresenter!!
    }


    private var accountPresenter: AccountPresenter? = null


    override val root: Parent = vbox {
        button("account"){
        }
    }
}