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

    override fun providePresenter(): SectionPresenter {
        if (musicPresenter == null){
            return MusicPresenter()
        }
        return musicPresenter!!
    }

    override fun filterView(text: String) {
        println("from music $text")
    }

    private var musicPresenter: MusicPresenter? = null

    override val root: Parent = vbox {
        button("music"){

        }
    }

}