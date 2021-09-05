package pro.osin.tools.games.qc.controller

import javafx.scene.media.Media
import tornadofx.*

class PlaybackService {

    fun playSound(type: SoundType) {
        val sound = PlaybackService::class.java.classLoader.getResource("sound/${type.resource}")
            ?.toExternalForm() ?: return
        val media = Media(sound)
        media.play()
    }

}

enum class SoundType(val resource: String) {
    MEGA_HEALTH_IN_5_SEC(AudioResourceConstants.MEGA_HEALTH_IN_5_SEC),
    MEGA_HEALTH_IN_10_SEC(AudioResourceConstants.MEGA_HEALTH_IN_10_SEC),
    RED_ARMOR_IN_5_SEC(AudioResourceConstants.RED_ARMOR_IN_5_SEC),
    RED_ARMOR_IN_10_SEC(AudioResourceConstants.RED_ARMOR_IN_10_SEC),
}

object AudioResourceConstants {

    const val MEGA_HEALTH_IN_5_SEC = "mega_in_5.mp3"
    const val MEGA_HEALTH_IN_10_SEC = "mega_in_10.mp3"
    const val RED_ARMOR_IN_5_SEC = "red_in_5.mp3"
    const val RED_ARMOR_IN_10_SEC = "red_in_10.mp3"

}