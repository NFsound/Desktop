package presentation.main_views.center

import application.SonusApplication
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.layout.GridPane
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox
import presentation.menu.item.MenuItem
import presentation.menu.list.Menu
import presentation.presenters.main.CenterPresenter
import presentation.presenters.main.MainPresenter
import presentation.presenters.sections.HomePresenter
import presentation.presenters.sections.SectionPresenter
import presentation.sections.SectionView
import presentation.sections.Sections
import presentation.sections.account.AccountViewImpl
import presentation.sections.home.HomeView
import presentation.sections.home.HomeViewImpl
import presentation.sections.music.MusicViewImpl
import presentation.sections.news.NewsViewImpl
import tornadofx.*
import javax.inject.Inject

class CenterMenuPlacementView(): View(), CenterView {

    @Inject
    lateinit var menu: Menu

    @Inject
    lateinit var sections: Sections

    init {
        SonusApplication.getInstance().applicationComponent.inject(this)
    }

    var placement:View = sections.sections[0] as View


    override val root = vbox {
        this.add(placement)
    }


    override fun setSection(menuItem: MenuItem) {
        val section = sections.sections.last {
            it.sectionTitle == menuItem.title }
        (placement).replaceWith(section as View)
        placement = section
    }

    override fun filter(text: String) {
        (placement as SectionView).providePresenter().filter(text)
    }

}