package pro.osin.tools.games.qc.listener

import org.jnativehook.keyboard.NativeKeyEvent
import org.jnativehook.keyboard.NativeKeyListener
import pro.osin.tools.games.qc.controller.SoundType
import java.time.OffsetDateTime
import java.util.*

class GlobalKeyListener : NativeKeyListener {

    val timerQueue: Queue<Item> = LinkedList()

    private val shortcutsQueue = LinkedHashSet<Int>()

    private val definedKeys = setOf(
        NativeKeyEvent.VC_X, NativeKeyEvent.VC_F1, NativeKeyEvent.VC_F2
    )

    override fun nativeKeyTyped(event: NativeKeyEvent?) {
        // not used
    }

    override fun nativeKeyPressed(event: NativeKeyEvent?) {
        val currentKeyCode = event?.keyCode ?: return

        if (!definedKeys.contains(currentKeyCode)) {
            return
        }

        if (shortcutsQueue.isEmpty()) {
            shortcutsQueue.add(currentKeyCode)
            return
        }

        shortcutsQueue.add(currentKeyCode)

        val in10sec = OffsetDateTime.now().plusSeconds(12)
        val in5sec = OffsetDateTime.now().plusSeconds(19)

        when {
            // mega
            shortcutsQueue.containsAll(setOf(NativeKeyEvent.VC_X, NativeKeyEvent.VC_F1)) -> {
                timerQueue.add(
                    Item(
                        listOf(
                            Notification(
                                SoundType.MEGA_HEALTH_IN_10_SEC,
                                in10sec
                            ),
                            Notification(
                                SoundType.MEGA_HEALTH_IN_5_SEC,
                                in5sec
                            )
                        )
                    )
                )
                shortcutsQueue.clear()
            }
            shortcutsQueue.containsAll(setOf(NativeKeyEvent.VC_X, NativeKeyEvent.VC_F2)) -> {
                timerQueue.add(
                    Item(
                        listOf(
                            Notification(
                                SoundType.RED_ARMOR_IN_10_SEC,
                                in10sec
                            ),
                            Notification(
                                SoundType.RED_ARMOR_IN_5_SEC,
                                in5sec
                            )
                        )
                    )
                )
                shortcutsQueue.clear()
            }
        }
    }

    override fun nativeKeyReleased(event: NativeKeyEvent?) {
        // not used
    }

}

data class Notification(
    val type: SoundType,
    val time: OffsetDateTime
)

data class Item(
    val notifications: List<Notification>
)