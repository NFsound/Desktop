package di.components

import application.SonusApplication
import dagger.Component
import di.modules.menu.MenuModule
import di.modules.navigation.NavigationModule
import di.modules.sections.ViewModule
import presentation.main_views.LeftMenuView
import presentation.main_views.center.CenterMenuPlacementView
import presentation.navigation.NavigatorImpl
import presentation.presenters.main.MainPresenter
import presentation.presenters.sections.SectionPresenter
import presentation.sections.SectionView

@Component(modules = [MenuModule::class,
    ViewModule::class,
    NavigationModule::class])
interface ApplicationComponent {

    fun inject(sonusApplication: SonusApplication)

    fun inject(leftMenuView: LeftMenuView)

    fun inject(sectionView: SectionView)

    fun inject(centerMenuPlacementView: CenterMenuPlacementView)

    fun inject(mainPresenter: MainPresenter)

    fun inject(sectionPresenter: SectionPresenter)

    fun inject(navigatorImpl: NavigatorImpl)


}