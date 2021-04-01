package presentation.main_views.main_window.window_states

import javafx.geometry.Dimension2D
import javafx.geometry.Point2D
import javafx.geometry.Rectangle2D
import javafx.stage.Screen
import javafx.stage.Stage

class MaximizedState(
    private val primaryStage: Stage) : State {

    val leftUpperPoint: Point2D
    val dimensions: Dimension2D

    override fun changeState(windowState: WindowState): State {
        return when (windowState) {
            WindowState.MAXIMIZED -> CustomState(primaryStage,this)
            WindowState.CUSTOM -> CustomState(primaryStage,  this)
        }
    }

    init {
        dimensions = Dimension2D(primaryStage.width, primaryStage.height)
        leftUpperPoint = Point2D(primaryStage.x, primaryStage.y)

        val primaryScreenBounds: Rectangle2D = Screen.getPrimary().visualBounds
        primaryStage.x = primaryScreenBounds.minX
        primaryStage.y = primaryScreenBounds.minY

        primaryStage.width = primaryScreenBounds.width
        primaryStage.height = primaryScreenBounds.height
    }
}