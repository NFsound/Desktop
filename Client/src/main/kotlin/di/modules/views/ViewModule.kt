package di.modules.views

import dagger.Module
import dagger.Provides
import presentation.main_views.sides.BottomMenuView
import presentation.main_views.sides.LeftMenuView
import presentation.main_views.sides.TopMenuView
import presentation.main_views.center.CenterMenuPlacementView
import presentation.main_views.center.CenterView
import presentation.sections.common.Sections
import presentation.sections.account.AccountView
import presentation.sections.account.AccountViewImpl
import presentation.sections.home.HomeView
import presentation.sections.home.HomeViewImpl
import presentation.sections.music.MusicView
import presentation.sections.music.MusicViewImpl
import presentation.sections.news.NewsView
import presentation.sections.news.NewsViewImpl

@Module
class ViewModule {

    @Provides
    fun provideCenterView(): CenterView = CenterMenuPlacementView()

    @Provides
    fun provideAccountView(): AccountView = accountSection

    @Provides
    fun provideNewsView(): NewsView = newsSection

    @Provides
    fun provideHomeView(): HomeView = homeSection

    @Provides
    fun provideMusicView(): MusicView = musicSection

    @Provides
    fun provideBottomView(): BottomMenuView = bottomMenuView

    @Provides
    fun provideLeftMenuView(): LeftMenuView = leftMenuView

    @Provides
    fun provideTopMenuView(): TopMenuView = topMenuView

    @Provides
    fun provideSections() = Sections(
        listOf(homeSection, musicSection, newsSection, accountSection)
    )

    companion object{
        //unique
        //в них внутренние inject, поэтому lazy
        private val homeSection by lazy{HomeViewImpl()}
        private val musicSection by lazy{MusicViewImpl()}
        private val newsSection by lazy{NewsViewImpl()}
        private val accountSection by lazy{AccountViewImpl()}

        private val bottomMenuView by lazy { BottomMenuView() }
        private val topMenuView by lazy { TopMenuView() }
        private val leftMenuView by lazy { LeftMenuView() }
    }
}