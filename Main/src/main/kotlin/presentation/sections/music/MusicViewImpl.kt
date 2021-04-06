package presentation.sections.music

import presentation.presenters.sections.AccountPresenter
import presentation.presenters.sections.MusicPresenter
import presentation.presenters.sections.SectionPresenter

class MusicViewImpl():MusicView {

    override var sectionTitle = "Music"

    override fun providePresenter(): SectionPresenter {
        if (musicPresenter == null){
            return MusicPresenter()
        }
        return musicPresenter!!
    }


    private var musicPresenter: MusicPresenter? = null

}