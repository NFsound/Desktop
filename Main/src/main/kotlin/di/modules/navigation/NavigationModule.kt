package di.modules.navigation
import dagger.Module
import dagger.Provides
import presentation.navigation.Navigator
import presentation.navigation.NavigatorImpl

@Module
class NavigationModule {

    @Provides
    fun getNavigator(): Navigator = NavigatorImpl()
}