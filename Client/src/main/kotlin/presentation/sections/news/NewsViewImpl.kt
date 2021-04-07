package presentation.sections.news

import javafx.scene.Parent
import presentation.presenters.sections.NewsPresenter
import presentation.presenters.sections.SectionPresenter
import tornadofx.View
import tornadofx.button
import tornadofx.vbox

class NewsViewImpl(): View(),NewsView {

    override var sectionTitle = "News"

    override fun providePresenter(): SectionPresenter {
        if (newsPresenter == null){
            return NewsPresenter()
        }
        return newsPresenter!!
    }


    private var newsPresenter: NewsPresenter? = null

    override val root: Parent = vbox {
        button("news"){
        }
    }

}