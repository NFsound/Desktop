package presentation.main_views.main_window.window_states

import javafx.geometry.Dimension2D
import javafx.geometry.Point2D

class InitialState(x: Double, y: Double, width: Double, height: Double) : State {
    override fun changeState(windowState: WindowState): State {
        throw UnsupportedOperationException("Error")
    }

    val leftUpperPoint: Point2D = Point2D(x,y)
    val dimensions: Dimension2D = Dimension2D(width, height)

}