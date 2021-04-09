package di.modules.interactors

import dagger.Binds
import dagger.Module
import interactors.AccountInteractor
import interactors.implementation.AccountInteractorImpl

@Module
abstract class InteractorsModule {

    @Binds
    abstract fun provideAccountInteractor(
        accountInteractorImpl: AccountInteractorImpl
    ): AccountInteractor

}