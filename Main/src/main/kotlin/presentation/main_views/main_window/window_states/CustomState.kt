package presentation.main_views.main_window.window_states

import javafx.stage.Stage

class CustomState(
    private val primaryStage: Stage,
     prevState: State?
) : State {

    override fun changeState(windowState: WindowState): State {
        return when (windowState) {
            WindowState.MAXIMIZED -> MaximizedState(primaryStage)
            WindowState.CUSTOM -> this
        }
    }


    init {
        when (prevState) {
            is MaximizedState -> {
                primaryStage.x = prevState.leftUpperPoint.x
                primaryStage.y = prevState.leftUpperPoint.y
                primaryStage.width = prevState.dimensions.width
                primaryStage.height = prevState.dimensions.height
            }
            is InitialState -> {
                primaryStage.x = prevState.leftUpperPoint.x
                primaryStage.y = prevState.leftUpperPoint.y
                primaryStage.width = prevState.dimensions.width
                primaryStage.height = prevState.dimensions.height
            }
        }
    }

}