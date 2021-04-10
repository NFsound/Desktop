package presentation.sections.account

import javafx.scene.Parent
import tornadofx.View
import tornadofx.*
class MessageWindow: View("Message") {
    override val root: Parent = vbox {
        label{
            text = "Your password has changed"
        }
        button{
            text = "Ok"
            action{
                this@MessageWindow.close()
            }
        }
    }
}