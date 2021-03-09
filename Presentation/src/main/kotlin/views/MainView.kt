package views

import javafx.scene.layout.BorderPane
import tornadofx.*

class MainView:View() {
    override val root : BorderPane by fxml("../main.fxml")
}