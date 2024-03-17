package br.com.teteukt.pitchfind.service

import android.content.Context
import android.media.SoundPool
import br.com.teteukt.pitchfind.R

class SoundPoolService(private val context: Context, private val soundPool: SoundPool) {

    private var pianoKeyA440SoundId: Int? = null
    private val _playingSounds = mutableListOf<Int>()

    init {
        load()
    }

    private fun load() {
        pianoKeyA440SoundId = soundPool.load(context, R.raw.piano_a_key_440, 1)
    }

    fun play(pitch: Float) {
        pianoKeyA440SoundId?.let { pianoKeyA440SoundId ->
            _playingSounds.add(soundPool.play(pianoKeyA440SoundId, 1F, 1F, 1, 0, pitch))
        }
    }

    fun stopAll() {
        _playingSounds.forEach {
            soundPool.stop(it)
        }
        _playingSounds.clear()
    }
}