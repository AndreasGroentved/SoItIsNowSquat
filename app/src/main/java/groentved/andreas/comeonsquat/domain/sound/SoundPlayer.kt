package groentved.andreas.comeonsquat.domain.sound

import android.annotation.SuppressLint
import android.content.Context
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import groentved.andreas.comeonsquat.R
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SoundPlayer @Inject constructor(private val context: Context) {
    private lateinit var soundPool: SoundPool
    private var mapOfAllSquatSounds = mapOf<Int, Int>()

    init {
        setUpSoundPool()
        loadAllSounds()
    }


    @SuppressLint("CheckResult")
    fun playSound(soundID: Int = drumSound) {
        val playId = mapOfAllSquatSounds[soundID] ?: throw RuntimeException("Sound not found")
        soundPool.play(playId, 1f, 1f, 111111, 0, 1f)
    }

    private fun loadAllSounds() {
        mapOfAllSquatSounds = allSounds.associate { id ->
            try {
                val soundId = soundPool.load(context, id, 1)
                id to soundId
            } catch (e: Exception) {
                println("unexpected file loading problem")
                id to -1
            }
        }.filterValues { it != -1 }
    }

    private fun setUpSoundPool() {
        if (lollipopOrGreater()) buildFromAPI21()
        else buildBeforeAPI21()
    }

    private fun lollipopOrGreater() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP

    @SuppressLint("NewApi")
    private fun buildFromAPI21() {
        val audioAttributes = AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).build()
        soundPool = SoundPool.Builder().setAudioAttributes(audioAttributes).setMaxStreams(20).build()
    }

    @Suppress("DEPRECATION")
    private fun buildBeforeAPI21() {
        soundPool = SoundPool(10, AudioManager.STREAM_MUSIC, 0)

    }

    companion object {
        const val drumSound: Int = R.raw.drum_squat
        val allSounds = listOf(drumSound)
    }

}