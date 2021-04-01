package application


import di.components.ApplicationComponent
import di.components.DaggerApplicationComponent
import presentation.styles.MainWindowStyles
import presentation.main_views.main_window.MainView
import presentation.styles.BottomViewStyles
import presentation.styles.LeftMenuStyles
import presentation.styles.TopViewStyles
import tornadofx.*

class SonusApplication : App(
    MainView::class,
    MainWindowStyles::class,
    BottomViewStyles::class,
    LeftMenuStyles::class,
    TopViewStyles::class
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

        const val resourcePath = "Main/src/main/resources/"
    }
}