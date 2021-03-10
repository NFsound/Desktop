package di.modules.menu
import dagger.Module
import dagger.Provides
import presentation.views.menu.item.MenuItemImpl
import presentation.views.menu.list.Menu
import presentation.views.menu.list.MenuList

@Module
class MenuModule {

    @Provides
    fun provideMenu():Menu{
        
        val list = listOf(MenuItemImpl("Home"),MenuItemImpl("Account"),)
        
        return MenuList(list)
    }

}