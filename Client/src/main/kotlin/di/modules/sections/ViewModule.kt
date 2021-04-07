package di.modules.sections

import dagger.Module
import dagger.Provides
import presentation.main_views.center.CenterMenuPlacementView
import presentation.main_views.center.CenterView
import presentation.sections.Sections
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
    fun provideSections() = Sections(
        listOf(homeSection, musicSection, newsSection, accountSection)
    )

    companion object{
        //unique
        private val homeSection = HomeViewImpl()
        private val musicSection = MusicViewImpl()
        private val newsSection = NewsViewImpl()
        private val accountSection = AccountViewImpl()
    }
}