package di.modules.sections

import dagger.Module
import dagger.Provides
import presentation.main_views.center.CenterMenuPlacementView
import presentation.main_views.center.CenterView
import presentation.sections.account.AccountViewImpl
import presentation.sections.home.HomeViewImpl
import presentation.sections.music.MusicViewImpl
import presentation.sections.news.NewsViewImpl

@Module
class ViewModule {

    @Provides
    fun provideCenterView(): CenterView = CenterMenuPlacementView()

}