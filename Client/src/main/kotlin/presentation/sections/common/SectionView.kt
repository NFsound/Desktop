package presentation.sections.common

import javafx.scene.Node
import presentation.presenters.sections.SectionPresenter

/**
 * Наследник всегда реализует View
 */
interface SectionView {

    var sectionTitle:String

    fun setPresenter(presenter: SectionPresenter)

    fun getPresenter():SectionPresenter

    fun showErrorMessage(text:String)

}