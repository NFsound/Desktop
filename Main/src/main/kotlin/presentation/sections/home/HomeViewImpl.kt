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


    override fun providePresenter(): SectionPresenter {
        if (homePresenter == null){
            return HomePresenter()
        }
        return homePresenter!!
    }


    private var homePresenter: HomePresenter? = null


    override val root: Parent = vbox {
        button("home"){
        }
    }

}