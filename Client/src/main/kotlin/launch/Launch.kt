package launch
import application.SonusApplication
import tornadofx.launch

class Launch {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch<SonusApplication>(args)
        }
    }
}

