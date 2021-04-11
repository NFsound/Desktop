package presentation.presenters.sections

import presentation.presenters.main.CenterPresenter

interface SectionPresenter {

    var centerPresenter: CenterPresenter


    fun provideCenterPresenter(centerPresenter: CenterPresenter){
        this.centerPresenter = centerPresenter
    }

    fun filter(text:String)

    fun onInitialLoad()
}