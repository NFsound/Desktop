package di.modules.menu
import application.SonusApplication
import dagger.Module
import dagger.Provides
import presentation.menu.item.MenuItemImpl
import presentation.menu.list.Menu
import presentation.menu.list.MenuList
import utils.IconsProvider


@Module
class MenuModule {

    @Provides
    fun provideMenu():Menu{

        val homeIconSVGPath = IconsProvider.getSVGPath(SonusApplication.resourcePath + "icons/home_icon_path.txt")
        val list = listOf(MenuItemImpl("Feed", homeIconSVGPath),
           MenuItemImpl("Home",homeIconSVGPath),
            MenuItemImpl("Account",homeIconSVGPath),
            MenuItemImpl("My music",homeIconSVGPath)
            )
        return MenuList(list)
    }




}