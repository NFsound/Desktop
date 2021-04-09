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


/*    override fun providePresenter(): SectionPresenter {
        if (homePresenter == null){
            return HomePresenter()
        }
        return homePresenter!!
    }*/

    override fun filterView(text: String) {
        println("from home $text")
    }

    override fun setPresenter(presenter: SectionPresenter) {
        TODO("Not yet implemented")
    }

    override fun getPresenter(): SectionPresenter {
        TODO("Not yet implemented")
    }

    private var homePresenter: HomePresenter? = null


    override val root: Parent = vbox {
        button("home"){
            prefWidth = 200.0
        }
        button{ }
    }

}