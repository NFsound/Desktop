package di.components

import application.SonusApplication
import dagger.Component
import di.modules.menu.MenuModule
import di.modules.navigation.NavigationModule
import di.modules.views.ViewModule
import presentation.main_views.BottomMenuView
import presentation.main_views.LeftMenuView
import presentation.main_views.TopMenuView
import presentation.main_views.center.CenterMenuPlacementView
import presentation.main_views.main_window.MainView
import presentation.navigation.NavigatorImpl
import presentation.presenters.main.MainPresenter
import presentation.presenters.sections.*

@Component(modules = [MenuModule::class,
    ViewModule::class,
    NavigationModule::class])
interface ApplicationComponent {
    //center injection
    fun inject(sonusApplication: SonusApplication)

    //main views
    fun inject(mainView: MainView)
    fun inject(leftMenuView: LeftMenuView)
    fun inject(bottomMenuView: BottomMenuView)
    fun inject(topMenuView: TopMenuView)
    fun inject(centerMenuPlacementView: CenterMenuPlacementView)

    //presenters
    fun inject(mainPresenter: MainPresenter)
    fun inject(homePresenter: HomePresenter)
    fun inject(accountPresenter: AccountPresenter)
    fun inject(musicPresenter: MusicPresenter)
    fun inject(newsPresenter: NewsPresenter)

    //nav
    fun inject(navigatorImpl: NavigatorImpl)


}