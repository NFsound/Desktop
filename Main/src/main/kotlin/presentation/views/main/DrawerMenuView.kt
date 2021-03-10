package presentation.views.main

import application.SonusApplication
import javafx.scene.layout.Region
import javafx.scene.shape.Shape
import presentation.menu.list.Menu
import presentation.styles.MainWindowStyles
import presentation.styles.MainWindowStyles.Companion.alternativeColor
import tornadofx.*
import java.awt.Color
import javax.inject.Inject

class DrawerMenuView(): View() {
    @Inject
    lateinit var menu: Menu

    lateinit var svgPaths: List<SVGIcon>
    init {
        SonusApplication.getInstance().applicationComponent.inject(this)
    }


    override var root = vbox{
        menu.menuList.forEach{

            hbox {
                rectangle{
                    width = 2.0
                    height = 30.0
                }
                region {
                    addClass(MainWindowStyles.leftMenuIconStyle)
                    svgicon(it.svgPath, size = 30, MainWindowStyles.white){
                        addClass(MainWindowStyles.iconStyle)

                    }
                }
                button(it.title){
                    addClass(MainWindowStyles.leftMenuItemStyle)
                }
            }

        }
        addClass(MainWindowStyles.leftMenuStyle)
    }
    
}