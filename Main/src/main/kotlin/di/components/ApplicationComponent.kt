package di.components

import application.SonusApplication
import dagger.Component
import di.modules.menu.MenuModule
import presentation.main_views.DrawerMenuView

@Component(modules = [MenuModule::class])
interface ApplicationComponent {

    fun inject(sonusApplication: SonusApplication)

    fun inject(drawerMenuView: DrawerMenuView)

}