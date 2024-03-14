package br.com.teteukt.pitchfind.service

import android.content.Context
import android.media.AudioManager
import android.media.SoundPool
import androidx.activity.ComponentActivity
import br.com.teteukt.pitchfind.R

class SoundPoolService(private val context: Context, private val soundPool: SoundPool) {

    private var pianoKeyA440SoundId: Int? = null

    init {
        load()
    }

    private fun load() {
        pianoKeyA440SoundId = soundPool.load(context, R.raw.piano_a_key_440, 1)
    }

    fun play(pitch: Float) {
        val audioManager = context.getSystemService(ComponentActivity.AUDIO_SERVICE) as AudioManager
        val actVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
        val maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        val volume: Float = (actVolume / maxVolume).toFloat()

        pianoKeyA440SoundId?.let { pianoKeyA440SoundId ->
            soundPool.play(pianoKeyA440SoundId, volume, volume, 1, 0, pitch)
        }

    }
}