package presentation.sections

import javafx.scene.Node
import presentation.presenters.sections.SectionPresenter

/**
 * Наследник всегда реализует View
 */
interface SectionView {

    var sectionTitle:String

    fun setPresenter(presenter: SectionPresenter)

    fun getPresenter():SectionPresenter
}