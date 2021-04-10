package di.modules.interactors

import dagger.Binds
import dagger.Module
import interactors.AccountInteractor
import interactors.HomeInteractor
import interactors.MusicInteractor
import interactors.NewsInteractor
import interactors.implementation.AccountInteractorImpl
import interactors.implementation.HomeInteractorImpl
import interactors.implementation.MusicInteractorImpl
import interactors.implementation.NewsInteractorImpl

@Module
abstract class InteractorsModule {

    @Binds
    abstract fun provideAccountInteractor(
        accountInteractorImpl: AccountInteractorImpl
    ): AccountInteractor

    @Binds
    abstract fun provideHomeInteractor(
        homeInteractorImpl: HomeInteractorImpl
    ): HomeInteractor

    @Binds
    abstract fun provideMusicInteractor(
        musicInteractorImpl: MusicInteractorImpl
    ): MusicInteractor

    @Binds
    abstract fun provideNewsInteractor(
        newsInteractorImpl: NewsInteractorImpl
    ): NewsInteractor


}