package br.com.teteukt.pitchfind

import java.util.Random

enum class NoteKey(val nomenclature: String, val frequency: Float) {
    C("C", 261.62552F),
    C_SHARP("C#", 277.18265F),
    D("D", 293.66473F),
    D_SHARP("D#", 311.12698F),
    E("E", 329.62753F),
    F("F", 349.22824F),
    F_SHARP("F#", 369.9944F),
    G("G", 391.9954F),
    G_SHARP("G#", 415.3047F),
    A("A", 440.0F),
    A_SHARP("A#", 466.1638F),
    B("B", 493.8833F);

    fun getRatioFrom(noteKey: NoteKey): Float {
        return noteKey.frequency / this.frequency
    }

    fun getNext(): NoteKey {
        return with(NoteKey.entries) {
            val thisIndex = indexOf(this@NoteKey)
            if(thisIndex >= size - 1)
                this@with[0]
            else
                this[thisIndex + 1]
        }
    }

    fun nomenclatureAsFlat() = this.nomenclature.replace("#", "b")

    companion object {
        fun getRandomNote(): NoteKey {
            return with(NoteKey.entries) {
                val randomIndex: Int = Random().nextInt(size)
                this[randomIndex]
            }
        }
    }
}