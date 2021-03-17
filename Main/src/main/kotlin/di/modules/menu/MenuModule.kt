package di.modules.menu
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
        val homeIconSVGPath = IconsProvider.getSVGPath("Main/src/main/resources/icons/home_icon_path.txt")
        val list = listOf(MenuItemImpl("Home", homeIconSVGPath),
           MenuItemImpl("Account",homeIconSVGPath))
        return MenuList(list)
    }




}