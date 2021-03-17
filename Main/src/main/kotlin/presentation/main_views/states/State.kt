package presentation.main_views.states

interface State {

    fun changeState(windowState: WindowState):State

}