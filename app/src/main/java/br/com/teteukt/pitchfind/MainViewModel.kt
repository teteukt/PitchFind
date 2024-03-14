package br.com.teteukt.pitchfind

import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import br.com.teteukt.pitchfind.service.SoundPoolService

class MainViewModel(
    private val mediaPlayer: MediaPlayer,
    private val soundPoolService: SoundPoolService
) : ViewModel() {
    fun playNote(noteKey: NoteKey) {
        soundPoolService.play(NoteKey.A.getRatioFrom(noteKey))
    }
}