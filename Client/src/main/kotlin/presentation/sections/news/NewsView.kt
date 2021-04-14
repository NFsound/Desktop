package presentation.sections.news

import models.core.news.News
import presentation.sections.common.SectionView

interface NewsView: SectionView {

    fun showNews(news:List<News>)

}