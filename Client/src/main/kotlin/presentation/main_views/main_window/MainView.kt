package presentation.main_views.main_window


import application.SonusApplication
import javafx.scene.layout.BorderPane
import javafx.stage.StageStyle
import presentation.main_views.BottomMenuView
import presentation.main_views.center.CenterMenuPlacementView
import presentation.main_views.LeftMenuView
import presentation.main_views.TopMenuView
import presentation.menu.item.MenuItem
import presentation.menu.list.Menu
import presentation.presenters.main.CenterPresenter
import presentation.presenters.main.MainPresenter
import presentation.sections.SectionView
import presentation.sections.account.AccountViewImpl
import presentation.sections.home.HomeViewImpl
import presentation.sections.music.MusicViewImpl
import presentation.sections.news.NewsViewImpl
import presentation.styles.MainWindowStyles
import tornadofx.*
import utils.ImageProvider
import utils.ResizeHelper
import javax.inject.Inject


class MainView() : View() {

    @Inject
    lateinit var menu: Menu


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
    var leftMenuView = LeftMenuView()
    var topMenuView = TopMenuView()
    var bottomMenuView = BottomMenuView()

    init {
        SonusApplication.getInstance().applicationComponent.inject(this)
        root.bottom{
            this.add(bottomMenuView)
        }
        root.left{
            leftMenuView.mainPresenter = mainPresenter as MainPresenter
            this.add(leftMenuView)
        }
        root.top {
            this.add(topMenuView)
        }
        root.center<CenterMenuPlacementView>()
    }







}
