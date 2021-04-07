package presentation.main_views.main_window.window_states

interface State {

    fun changeState(windowState: WindowState):State

}