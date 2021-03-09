package application


import components.DaggerApplicationComponent
import tornadofx.*
import views.MainView

class SonusApplication: App(MainView::class) {
    val applicationComponent = DaggerApplicationComponent.builder().build()

}