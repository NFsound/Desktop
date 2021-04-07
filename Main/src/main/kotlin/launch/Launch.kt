package launch
import application.SonusApplication
import tornadofx.launch

class Launch {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(Launch::class.java.`package`)
            launch<SonusApplication>(args)
        }
    }
}

