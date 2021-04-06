package presentation.main_views.main_window


import javafx.scene.layout.BorderPane
import javafx.stage.StageStyle
import presentation.main_views.BottomMenuView
import presentation.main_views.center.CenterMenuPlacementView
import presentation.main_views.LeftMenuView
import presentation.main_views.TopMenuView
import presentation.styles.MainWindowStyles
import tornadofx.*
import utils.ImageProvider
import utils.ResizeHelper


class MainView() : View() {
    override val root: BorderPane = borderpane {
        primaryStage.title = "Sonus"
        addClass(MainWindowStyles.mainStyle)
        primaryStage.initStyle(StageStyle.UNDECORATED)
        primaryStage.isResizable = true
        primaryStage.minHeight = MainWindowStyles.minHeightMain.toDouble()
        primaryStage.minWidth = MainWindowStyles.minWidthMain.toDouble()
        primaryStage.icons.add(ImageProvider.getImage("img/main_icon2.png"))
    }

    override fun onBeforeShow() {
        super.onBeforeShow()
        ResizeHelper.addResizeListener(primaryStage)
    }

    init {
        root.bottom<BottomMenuView>()
        root.left<LeftMenuView>()
        root.top<TopMenuView>()
        root.center<CenterMenuPlacementView>()
    }

}