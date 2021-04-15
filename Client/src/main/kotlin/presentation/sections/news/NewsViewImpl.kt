package presentation.sections.news

import javafx.scene.control.ScrollPane
import javafx.scene.layout.VBox
import models.core.news.News
import presentation.presenters.sections.NewsPresenter
import presentation.presenters.sections.SectionPresenter
import presentation.sections.account.MessageWindow
import presentation.styles.sections.NewsViewStyles
import presentation.styles.sections.NewsViewStyles.Companion.newsTitleStyle
import presentation.styles.sections.NewsViewStyles.Companion.newsVBoxStyle
import tornadofx.*
import tornadofx.Stylesheet.Companion.textArea
import tornadofx.Stylesheet.Companion.textInput


class NewsViewImpl() : View(), NewsView {

    lateinit var mainBox: VBox

    override fun showNews(news: List<News>) {
        mainBox.children.removeAll { true }
        for (obj in news) {
            mainBox.vbox {
                addClass(newsVBoxStyle)
                label(obj.title) {
                    addClass(NewsViewStyles.newsTitleStyle)
                }
                textarea(obj.message) {
                    addClass(NewsViewStyles.newsTextStyle)
                    removeClass(textInput)
                    removeClass(textArea)
                    isWrapText = true
                    isEditable = false
                    usePrefHeight = true
                }
            }
        }
    }

    override var sectionTitle = "News"


    override fun setPresenter(presenter: SectionPresenter) {
        newsPresenter = presenter as NewsPresenter
        newsPresenter.onInitialLoad()
    }

    override fun getPresenter(): SectionPresenter {
        return newsPresenter
    }


    private lateinit var newsPresenter: NewsPresenter

    override val root: ScrollPane = scrollpane {
        addClass(NewsViewStyles.mainScrollViewStyle)
        isFitToWidth = true
        isFitToHeight = true
        mainBox = vbox {
            addClass(NewsViewStyles.mainVBoxStyle)
            isFillWidth = true
            isFitToHeight = true
            label("No news yet. Check your Internet connection") {
                addClass(newsTitleStyle)
            }
        }
    }

    override fun showErrorMessage(text: String) {
        openInternalWindow(
            MessageWindow(text),
            owner = this.root.parent.parent
        )
    }

    init {

    }

}