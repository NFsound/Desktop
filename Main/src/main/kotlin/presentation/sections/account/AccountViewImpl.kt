package presentation.sections.account

import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.layout.GridPane
import presentation.presenters.AccountPresenter
import tornadofx.*
import javax.inject.Inject

class AccountViewImpl: View(), AccountView {

    @Inject
    lateinit var accountPresenter: AccountPresenter

    override val root: Parent = vbox {
        button{

        }
    }
}