package presentation.main_views


import presentation.styles.MainWindowStyles
import tornadofx.*
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.streams.toList


class MainView() : View() {

    override val root = borderpane {
        addClass(MainWindowStyles.mainStyle)
    }//by fxml("../../../main.fxml")
    fun getSVGPath(filepath:String):String{
        return Files.lines(Paths.get(filepath), StandardCharsets.UTF_8).toList()[0];
    }
    init {
        root.left<DrawerMenuView>()
        root.center<CenterMenuPlacementView>()
    }

}