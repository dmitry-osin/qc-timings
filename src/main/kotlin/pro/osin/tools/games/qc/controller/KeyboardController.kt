package pro.osin.tools.games.qc.controller

import pro.osin.tools.games.qc.listener.Item
import tornadofx.Controller
import java.util.*
import kotlin.concurrent.schedule

class KeyboardController: Controller() {

    private val timers = ArrayList<Timer>()
    private val playbackService = PlaybackService()

    fun processTimers(queue: Queue<Item>) {
        val item = queue.poll() ?: return
        item.notifications.forEach {
            val timer = Timer()
            timers += timer
            val instant = it.time.toInstant()
            timer.schedule(Date.from(instant)) {
                playbackService.playSound(it.type)
            }
        }
    }

}