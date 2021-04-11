package presentation.sections.news

import models.core.News
import presentation.sections.SectionView

interface NewsView:SectionView {

    fun showNews(news:List<News>)

}