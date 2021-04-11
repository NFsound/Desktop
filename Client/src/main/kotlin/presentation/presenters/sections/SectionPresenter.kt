package presentation.presenters.sections

interface SectionPresenter {

    fun filter(text:String)

    fun onInitialLoad()
}