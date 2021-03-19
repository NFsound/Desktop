package presentation.main_views


import javafx.scene.Scene
import javafx.scene.layout.BorderPane
import javafx.stage.StageStyle
import presentation.styles.MainWindowStyles
import tornadofx.*
import utils.ResizeHelper


class MainView() : View() {
    override val root: BorderPane = borderpane {
        addClass(MainWindowStyles.mainStyle)
        primaryStage.initStyle(StageStyle.UNDECORATED)
        primaryStage.isResizable = true
        primaryStage.minHeight = MainWindowStyles.minHeightMain.toDouble()
        primaryStage.minWidth = MainWindowStyles.minWidthMain.toDouble()

    }//by fxml("../../../main.fxml")

    override fun onBeforeShow() {
        super.onBeforeShow()
        ResizeHelper.addResizeListener(primaryStage)

    }

    init {
        root.bottom<BottomMenuView>()
        root.left<DrawerMenuView>()
        root.top<TopMenuView>()
        //root.center<CenterMenuPlacementView>()

    }

}