package presentation.sections.home

import javafx.scene.Parent
import presentation.presenters.sections.AccountPresenter
import presentation.presenters.sections.HomePresenter
import presentation.presenters.sections.SectionPresenter
import tornadofx.View
import tornadofx.button
import tornadofx.vbox

class HomeViewImpl: View(),HomeView {

    override var sectionTitle = "Home"


    override fun setPresenter(presenter: SectionPresenter) {
        homePresenter = presenter as HomePresenter
        homePresenter.onInitialLoad()
    }

    override fun getPresenter(): SectionPresenter {
        return homePresenter
    }

    private lateinit var homePresenter: HomePresenter


    override val root: Parent = vbox {
        button("home"){
            prefWidth = 200.0
        }
        button{ }
    }

}