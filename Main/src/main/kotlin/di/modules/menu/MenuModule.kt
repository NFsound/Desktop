package di.modules.menu
import dagger.Module
import dagger.Provides
import presentation.menu.item.MenuItemImpl
import presentation.menu.list.Menu
import presentation.menu.list.MenuList
import java.io.InputStream
import java.io.BufferedReader

import java.io.InputStreamReader

import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.streams.toList


@Module
class MenuModule {

    @Provides
    fun provideMenu():Menu{
        val homeIconSVGPath = getSVGPath("Main/src/main/resources/icons/home_icon_path.txt")
        val list = listOf(MenuItemImpl("Home", homeIconSVGPath),
           MenuItemImpl("Account",homeIconSVGPath))
        return MenuList(list)
    }


    private fun getSVGPath(filepath:String):String{
        return Files.lines(Paths.get(filepath), StandardCharsets.UTF_8).toList()[0];
    }

}