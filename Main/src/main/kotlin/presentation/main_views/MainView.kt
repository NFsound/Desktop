package presentation.main_views


import presentation.styles.MainWindowStyles
import tornadofx.*


class MainView() : View() {
    override val root = borderpane {
        addClass(MainWindowStyles.mainStyle)
    }//by fxml("../../../main.fxml")

    init {
        root.left<DrawerMenuView>()
        root.center<CenterMenuPlacementView>()
    }

}