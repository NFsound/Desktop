package presentation.views.main

import application.SonusApplication
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.control.Control
import javafx.scene.layout.BorderPane
import javafx.scene.layout.Pane
import presentation.views.menu.list.Menu
import tornadofx.*
import javax.inject.Inject


class DrawerMenuView(): View() {
    @Inject
    lateinit var menu: Menu
    init {
        SonusApplication.getInstance().applicationComponent.inject(this)
    }
    
    override var root = drawer{

        menu.menuList.forEach {
            val title = SimpleStringProperty(it.title)
            this@drawer.add(DrawerItem(this,title,showHeader = false))
        }
    }
    
}