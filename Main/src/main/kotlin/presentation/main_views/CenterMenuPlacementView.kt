package presentation.main_views

import javafx.scene.Parent
import javafx.scene.layout.BorderPane
import javafx.scene.layout.Pane
import presentation.menu.list.Menu
import presentation.navigation.Navigator
import presentation.sections.account.AccountViewImpl
import tornadofx.*
import javax.inject.Inject

class CenterMenuPlacementView(): View() {

    @Inject
    lateinit var menu: Menu

    @Inject
    lateinit var navigator: Navigator



    override val root: Pane = vbox {
        this.add(AccountViewImpl())
    }
}