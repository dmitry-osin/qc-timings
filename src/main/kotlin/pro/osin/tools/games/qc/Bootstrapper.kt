package pro.osin.tools.games.qc

import org.jnativehook.GlobalScreen
import pro.osin.tools.games.qc.view.MainView
import tornadofx.App
import tornadofx.launch
import java.util.logging.Level
import java.util.logging.Logger


class Bootstrapper: App(primaryView = MainView::class) {

    init {
        val logger = Logger.getLogger(GlobalScreen::class.java.getPackage().name)
        logger.level = Level.WARNING
        logger.useParentHandlers = false
    }


    override fun stop() {
        GlobalScreen.unregisterNativeHook()
        System.exit(0)
    }

}

fun main(args: Array<String>) {
    launch<Bootstrapper>(args)
}