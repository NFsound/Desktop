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

    override fun setPresenter(presenter: SectionPresenter) {
        musicPresenter = presenter as MusicPresenter
        musicPresenter.onInitialLoad()
    }

    override fun getPresenter(): SectionPresenter {
        return musicPresenter
    }

    private lateinit var musicPresenter: MusicPresenter

    override val root: Parent = vbox {
        button("music"){

        }
    }

}