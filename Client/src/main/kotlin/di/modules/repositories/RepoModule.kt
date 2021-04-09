package di.modules.repositories

import dagger.Binds
import dagger.Module
import repositories.AccountRepository
import repositories.implementations.AccountRepositoryImpl

@Module
abstract class RepoModule {

    @Binds
    abstract fun getAccountRepository(accountRepository: AccountRepositoryImpl)
            : AccountRepository
}