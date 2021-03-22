package presentation.main_views


import application.SonusApplication
import javafx.scene.Scene
import javafx.scene.layout.BorderPane
import javafx.stage.StageStyle
import javafx.stage.Window
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
        primaryStage.icons.removeAll{true}
        primaryStage.icons.add(ImageProvider.getImage("img/main_icon2.png"))
        
    }

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