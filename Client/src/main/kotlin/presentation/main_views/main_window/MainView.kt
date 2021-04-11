package presentation.main_views.main_window


import application.SonusApplication
import javafx.scene.layout.BorderPane
import javafx.stage.StageStyle
import presentation.main_views.sides.BottomMenuView
import presentation.main_views.center.CenterMenuPlacementView
import presentation.main_views.sides.LeftMenuView
import presentation.main_views.sides.TopMenuView
import presentation.menu.list.Menu
import presentation.presenters.main.CenterPresenter
import presentation.presenters.main.MainPresenter
import presentation.sections.Sections
import presentation.styles.sides.MainWindowStyles
import tornadofx.*
import utils.ImageProvider
import utils.ResizeHelper
import javax.inject.Inject


class MainView() : View() {

    @Inject
    lateinit var menu: Menu

    @Inject
    lateinit var sections: Sections

    @Inject
    lateinit var leftMenuView: LeftMenuView
    @Inject
    lateinit var topMenuView: TopMenuView
    @Inject
    lateinit var bottomMenuView: BottomMenuView

    private var mainPresenter: CenterPresenter? = provideMainPresenter()

    fun provideMainPresenter(): CenterPresenter {
        return mainPresenter?: MainPresenter()
    }

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
    //views


    init {
        SonusApplication.getInstance().applicationComponent.inject(this)
        root.bottom{
            this.add(bottomMenuView.apply {setPresenter(mainPresenter!!)})
        }
        root.left{
            this.add(leftMenuView.apply {setPresenter(mainPresenter!!)})
        }
        root.top {
            this.add(topMenuView.apply {setPresenter(mainPresenter!!)})
        }
        root.center<CenterMenuPlacementView>()
        mainPresenter!!.onSectionSelected(menu.menuList[0],0)
        for (section in sections.sections){
            section.getPresenter().provideCenterPresenter(mainPresenter!!)
        }
    }








}
