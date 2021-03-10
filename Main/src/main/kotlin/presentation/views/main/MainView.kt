package presentation.views.main

import javafx.scene.Parent
import javafx.scene.layout.BorderPane
import tornadofx.Drawer
import tornadofx.Stylesheet.Companion.left

import tornadofx.View
import tornadofx.borderpane
import tornadofx.style

class MainView(): View() {
    override val root: BorderPane by fxml("../../../main.fxml")
    init {

        this.root.left<DrawerMenuView>()
    }

}