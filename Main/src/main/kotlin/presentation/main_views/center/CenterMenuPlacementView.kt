package presentation.main_views.center

import application.SonusApplication
import javafx.scene.layout.Pane
import presentation.menu.item.MenuItem
import presentation.menu.list.Menu
import presentation.presenters.main.CenterPresenter
import presentation.presenters.main.MainPresenter
import presentation.presenters.sections.HomePresenter
import presentation.presenters.sections.SectionPresenter
import presentation.sections.SectionView
import tornadofx.*
import javax.inject.Inject

class CenterMenuPlacementView(): View(), CenterView {

    @Inject
    lateinit var menu: Menu


    override fun provideMainPresenter(): CenterPresenter {
        return mainPresenter?: MainPresenter()
    }

    private var mainPresenter: MainPresenter? = null


    init {
        SonusApplication.getInstance().applicationComponent.inject(this)
    }


    override val root: Pane = vbox {

    }



    override fun selectSection(menuItem: MenuItem) {
        root.children.removeAll{true}
        //root.add(getSectionByMenuItem(menuItem) as View)
    }



}