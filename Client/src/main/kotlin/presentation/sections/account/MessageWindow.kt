package presentation.sections.account

import javafx.scene.Parent
import tornadofx.View
import tornadofx.*
class MessageWindow(var messageText: String): View("Message") {
    override val root: Parent = vbox {
        label{
            text = messageText
        }
        button{
            text = "Ok"
            action{
                this@MessageWindow.close()
            }
        }
    }
}