package presentation.sections.news

import presentation.presenters.sections.NewsPresenter
import presentation.presenters.sections.SectionPresenter

class NewsViewImpl():NewsView {

    override var sectionTitle = "News"

    override fun providePresenter(): SectionPresenter {
        if (newsPresenter == null){
            return NewsPresenter()
        }
        return newsPresenter!!
    }


    private var newsPresenter: NewsPresenter? = null

}