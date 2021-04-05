package presentation.sections.home

import javafx.scene.Parent
import presentation.presenters.AccountPresenter
import presentation.presenters.HomePresenter
import tornadofx.View
import tornadofx.button
import tornadofx.vbox
import javax.inject.Inject

class HomeViewImpl: View(),HomeView {
    @Inject
    lateinit var homePresenter: HomePresenter

    override val root: Parent = vbox {
        button("home"){
        }
    }

}