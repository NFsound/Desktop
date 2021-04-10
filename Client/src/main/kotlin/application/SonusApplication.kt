package application


import di.components.ApplicationComponent
import di.components.DaggerApplicationComponent
import presentation.styles.sides.MainWindowStyles
import presentation.main_views.main_window.MainView
import presentation.styles.sections.AccountViewStyles
import presentation.styles.sides.BottomViewStyles
import presentation.styles.sides.LeftMenuStyles
import presentation.styles.sides.TopViewStyles
import tornadofx.*

class SonusApplication : App(
    MainView::class,
    MainWindowStyles::class,
    BottomViewStyles::class,
    LeftMenuStyles::class,
    TopViewStyles::class,
    AccountViewStyles::class
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