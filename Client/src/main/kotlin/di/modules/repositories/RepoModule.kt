package di.modules.repositories

import dagger.Binds
import dagger.Module
import repositories.*
import repositories.implementations.*

@Module
abstract class RepoModule {

    @Binds
    abstract fun getAccountRepository(accountRepository: AccountRepositoryImpl)
            : AccountRepository

    @Binds
    abstract fun getNewsRepository(newsRepositoryImpl: NewsRepositoryImpl)
            : NewsRepository

    @Binds
    abstract fun getPlaylistRepository(playlistRepositoryImpl: PlaylistRepositoryImpl)
            : PlaylistRepository

    @Binds
    abstract fun getTrackRepository(trackRepositoryImpl: TrackRepositoryImpl)
            : TrackRepository

    @Binds
    abstract fun getUtilsRepository(utilsRepositoryImpl: UtilsRepositoryImpl)
            : UtilsRepository
}