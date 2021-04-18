package launch
import application.SonusApplication
import javafx.application.Platform
import presentation.sections.account.MessageWindow
import tornadofx.launch
import java.lang.Exception

class Launch {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch<SonusApplication>(args)
        }
    }
}

