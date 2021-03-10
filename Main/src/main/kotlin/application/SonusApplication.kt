package application


import di.components.ApplicationComponent
import di.components.DaggerApplicationComponent
import presentation.views.main.MainView
import tornadofx.*
import java.lang.IllegalStateException

class SonusApplication : App(MainView::class) {

    var applicationComponent: ApplicationComponent

    init {
        INSTANCE = this
        applicationComponent = DaggerApplicationComponent
            .builder()
            .build()
        applicationComponent.inject(this)
    }

    companion object {

        private var INSTANCE: SonusApplication? = null

        fun getInstance(): SonusApplication {
            if (INSTANCE == null) {
                throw IllegalStateException("Instance not initialized")
            }
            return INSTANCE!!
        }
    }
}