package presentation.main_views


import javafx.scene.Node
import javafx.scene.layout.BorderPane
import presentation.styles.MainWindowStyles
import tornadofx.*
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.streams.toList


class MainView() : View() {

    override val root: BorderPane = borderpane {
        addClass(MainWindowStyles.mainStyle)
    }//by fxml("../../../main.fxml")
    init {
        root.bottom<BottomMenuView>()
        root.left<DrawerMenuView>()
        //root.center<CenterMenuPlacementView>()

    }

}