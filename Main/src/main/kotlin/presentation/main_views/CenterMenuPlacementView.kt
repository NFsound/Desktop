package presentation.main_views

import javafx.scene.Parent
import javafx.scene.layout.BorderPane
import javafx.scene.layout.Pane
import presentation.menu.list.Menu
import presentation.navigation.Navigator
import presentation.presenters.MainPresenter
import presentation.sections.SectionView
import presentation.sections.account.AccountViewImpl
import presentation.sections.home.HomeViewImpl
import tornadofx.*
import javax.inject.Inject

class CenterMenuPlacementView(): View() {

    @Inject
    lateinit var menu: Menu

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var presenter: MainPresenter

    @Inject
    lateinit var sectionViews: List<SectionView>

    var currentItem:View = AccountViewImpl()

    override val root: Pane = vbox {

        this.add(currentItem)
        setOnMouseClicked {
            currentItem = HomeViewImpl()
            this.children.removeAll{true}
            this.children
        }
    }

}