package presentation.main_views

import javafx.scene.Parent
import presentation.styles.TopViewStyles
import tornadofx.*


class TopMenuView: View() {
    private var xOffset:Double = 0.0
    private var yOffset:Double = 0.0
    override val root = hbox{
        addClass(TopViewStyles.topBarStyle)
        setOnMousePressed { event ->
            xOffset = event.getSceneX()
            yOffset = event.getSceneY()
        }
        setOnMouseDragged { event ->
            primaryStage.x = event.getScreenX() - xOffset
            primaryStage.y = event.getScreenY() - yOffset
        }
    }
}