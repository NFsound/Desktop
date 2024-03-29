package presentation.main_views.center

import application.SonusApplication
import presentation.menu.item.MenuItem
import presentation.menu.list.Menu
import presentation.presenters.sections.*
import presentation.sections.common.SectionView
import presentation.sections.common.Sections
import tornadofx.*
import javax.inject.Inject

class CenterMenuPlacementView(): View(), CenterView {

    @Inject
    lateinit var menu: Menu

    @Inject
    lateinit var sections: Sections

    private fun providePresenter(sect: SectionView): SectionPresenter {
        return when(sect.sectionTitle){
            "Account" -> AccountPresenter()
            "Home" -> HomePresenter()
            "Music" -> MusicPresenter()
            "News" -> NewsPresenter()
            else -> throw IllegalArgumentException()
        }
    }


    fun providePresenters(){
        for (sect in sections.sections){
            sect.setPresenter(providePresenter(sect))
        }
    }

    init {
        SonusApplication.getInstance().applicationComponent.inject(this)
        providePresenters()
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
        section.getPresenter().onInitialLoad()
    }

    override fun filter(text: String) {
        (placement as SectionView).getPresenter().filter(text)
    }

}