package application


import di.components.ApplicationComponent
import di.components.DaggerApplicationComponent
import javafx.scene.Node
import javafx.scene.Parent
import presentation.styles.sides.MainWindowStyles
import presentation.main_views.main_window.MainView
import presentation.styles.sections.AccountViewStyles
import presentation.styles.sections.HomeViewStyles
import presentation.styles.sections.NewsViewStyles
import presentation.styles.sides.BottomViewStyles
import presentation.styles.sides.LeftMenuStyles
import presentation.styles.sides.TopViewStyles
import tornadofx.*
import tornadofx.FX.Companion.primaryStage

class SonusApplication : App(
    MainView::class,
    MainWindowStyles::class,
    BottomViewStyles::class,
    LeftMenuStyles::class,
    TopViewStyles::class,
    AccountViewStyles::class,
    NewsViewStyles::class,
    HomeViewStyles::class
) {

    var applicationComponent: ApplicationComponent

    init {
        INSTANCE = this
        applicationComponent = DaggerApplicationComponent
            .builder()
            .build()
        applicationComponent.inject(this)
        reloadStylesheetsOnFocus()

    }

    companion object {

        private var INSTANCE: SonusApplication? = null

        fun getInstance(): SonusApplication {
            return INSTANCE!!
        }
        const val resourcePath = "Client/src/main/resources/"
    }
}