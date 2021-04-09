package presentation.sections.music

import javafx.scene.Parent
import presentation.presenters.sections.AccountPresenter
import presentation.presenters.sections.MusicPresenter
import presentation.presenters.sections.SectionPresenter
import presentation.styles.Colors
import tornadofx.View
import tornadofx.button
import tornadofx.vbox

class MusicViewImpl(): View(), MusicView {

    override var sectionTitle = "Music"



    override fun filterView(text: String) {
        println("from music $text")
    }

    override fun setPresenter(presenter: SectionPresenter) {
        TODO("Not yet implemented")
    }

    override fun getPresenter(): SectionPresenter {
        TODO("Not yet implemented")
    }

    private var musicPresenter: MusicPresenter? = null

    override val root: Parent = vbox {
        button("music"){

        }
    }

}