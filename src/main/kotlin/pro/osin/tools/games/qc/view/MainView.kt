package pro.osin.tools.games.qc.view

import javafx.geometry.Insets
import javafx.geometry.Pos
import org.jnativehook.GlobalScreen
import pro.osin.tools.games.qc.controller.KeyboardController
import pro.osin.tools.games.qc.listener.GlobalKeyListener
import pro.osin.tools.games.qc.view.MainViewConstants.STATUS_ACTIVE
import pro.osin.tools.games.qc.view.MainViewConstants.STATUS_IDLE
import tornadofx.*

class MainView : View("QC Timings") {

    private val globalKeyListener = GlobalKeyListener()

    private val keyboardController: KeyboardController by inject()

    private val startBtn = button(text = "start") {
        vboxConstraints {
            margin = Insets(10.0, 10.0, 10.0, 10.0)
        }
        action {
            GlobalScreen.registerNativeHook()
            GlobalScreen.addNativeKeyListener(globalKeyListener)
            statusLbl.text = STATUS_ACTIVE

            runAsync {
                while (true) {
                    keyboardController.processTimers(globalKeyListener.timerQueue)
                }
            }
        }
    }

    private val stopBtn = button(text = "stop") {
        vboxConstraints {
            margin = Insets(10.0, 10.0, 10.0, 10.0)
        }
        action {
            GlobalScreen.unregisterNativeHook()
            statusLbl.text = STATUS_IDLE
        }
    }

    private val statusLbl = label {
        hboxConstraints {
            margin = Insets(10.0, 10.0, 10.0, 5.0)
        }
        text = STATUS_IDLE
    }

    override val root = borderpane {
        top {
            vbox {
                label {
                    vboxConstraints {
                        margin = Insets(10.0, 10.0, 10.0, 10.0)
                    }
                    text = "Listening to keyboard events"
                }.style {
                    fontSize = Dimension(16.0, Dimension.LinearUnits.px)
                }
                hbox {
                    this += startBtn
                    this += stopBtn
                }.apply {
                    this.alignment = Pos.CENTER
                    this.spacing = 10.0
                }
                hbox {
                    label {
                        hboxConstraints {
                            margin = Insets(10.0, 5.0, 10.0, 10.0)
                        }
                        text = "status"
                    }
                    this += statusLbl
                }
            }
        }
    }

}

object MainViewConstants {

    const val STATUS_IDLE = "IDLE"
    const val STATUS_ACTIVE = "ACTIVE"

}
