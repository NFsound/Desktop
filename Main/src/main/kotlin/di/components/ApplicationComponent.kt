package di.components

import application.SonusApplication
import dagger.Component
import di.modules.menu.MenuModule
import presentation.main_views.LeftMenuView

@Component(modules = [MenuModule::class])
interface ApplicationComponent {

    fun inject(sonusApplication: SonusApplication)

    fun inject(leftMenuView: LeftMenuView)

}