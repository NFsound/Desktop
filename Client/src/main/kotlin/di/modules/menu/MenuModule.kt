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
        val feedIconSVGPath = IconsProvider.getSVGPath(SonusApplication.resourcePath + "icons/feed_icon_path.txt")
        val accountIconSVGPath = IconsProvider.getSVGPath(SonusApplication.resourcePath + "icons/account_icon_path.txt")
        val soundIconSVGPath = IconsProvider.getSVGPath(SonusApplication.resourcePath + "icons/sound_icon_path.txt")

        val list = listOf(MenuItemImpl("News", feedIconSVGPath),
           MenuItemImpl("Home",homeIconSVGPath),
            MenuItemImpl("Music",soundIconSVGPath),
            MenuItemImpl("Account",accountIconSVGPath)
            )
        return MenuList(list)
    }




}