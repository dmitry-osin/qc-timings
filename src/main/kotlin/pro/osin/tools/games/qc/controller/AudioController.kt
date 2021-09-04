package pro.osin.tools.games.qc.controller

import javafx.scene.media.Media
import tornadofx.Controller
import tornadofx.play

class AudioController : Controller() {

    fun playSound(type: SoundType) {
        val sound = AudioController::class.java.classLoader.getResource("sound/${type.resource}")
            ?.toExternalForm() ?: return
        val media = Media(sound)
        media.play()
    }

}

enum class SoundType(val resource: String) {
    MEGA_HEALTH_IN_5_SEC(AudioSourceConstants.MEGA_HEALTH_IN_5_SEC),
    MEGA_HEALTH_IN_10_SEC(AudioSourceConstants.MEGA_HEALTH_IN_10_SEC),
    RED_ARMOR_IN_5_SEC(AudioSourceConstants.RED_ARMOR_IN_5_SEC),
    RED_ARMOR_IN_10_SEC(AudioSourceConstants.RED_ARMOR_IN_10_SEC),
}

object AudioSourceConstants {

    const val MEGA_HEALTH_IN_5_SEC = "mega_in_5.mp3"
    const val MEGA_HEALTH_IN_10_SEC = "mega_in_10.mp3"
    const val RED_ARMOR_IN_5_SEC = "red_in_5.mp3"
    const val RED_ARMOR_IN_10_SEC = "red_in_10.mp3"

}