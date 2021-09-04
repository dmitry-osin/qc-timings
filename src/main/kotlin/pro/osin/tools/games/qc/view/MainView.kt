package pro.osin.tools.games.qc.view

import javafx.geometry.Insets
import org.jnativehook.GlobalScreen
import pro.osin.tools.games.qc.controller.KeyboardController
import pro.osin.tools.games.qc.listener.GlobalKeyListener
import tornadofx.*

class MainView : View("My View") {

    private val globalKeyListener = GlobalKeyListener()

    private val keyboardController: KeyboardController by inject()

    private val startBtn = button(text = "listen") {
        vboxConstraints {
            margin = Insets(10.0, 10.0, 10.0, 10.0)
        }
        action {
            GlobalScreen.registerNativeHook()
            GlobalScreen.addNativeKeyListener(globalKeyListener)

            runAsync {
                while (true) {
                    keyboardController.processTimers(globalKeyListener.timerQueue)
                }
            }
        }
    }

    private val stopBtn = button(text = "stop listen") {
        vboxConstraints {
            margin = Insets(10.0, 10.0, 10.0, 10.0)
        }
        action {
            GlobalScreen.unregisterNativeHook();
        }
    }

    override val root = borderpane {
        top {
            vbox {
                label {
                    text = "Listening to keyboard events"
                }
                this += startBtn
                this += stopBtn
                hbox {
                    label(text = "Mega shortcut")
                    textfield {  }
                }

                hbox {
                    label(text = "Red Armor shortcut")
                    textfield {  }
                }
            }
        }
    }



    init {

    }
}
