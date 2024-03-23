package br.com.teteukt.pitchfind.domain

import java.util.Random

enum class NoteKey(val sharpNomenclature: String, val flatNomenclature: String, val frequency: Float) {
    C("C", "C", 261.62552F),
    C_SHARP("C#", "D♭", 277.18265F),
    D("D", "D", 293.66473F),
    D_SHARP("D#", "E♭", 311.12698F),
    E("E", "E", 329.62753F),
    F("F", "F", 349.22824F),
    F_SHARP("F#", "G♭", 369.9944F),
    G("G", "G", 391.9954F),
    G_SHARP("G#", "A♭", 415.3047F),
    A("A", "A", 440.0F),
    A_SHARP("A#", "B♭", 466.1638F),
    B("B", "B", 493.8833F);

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

    fun getNext(count: Int): NoteKey {
        var note = this
        repeat(count) { note = getNext() }
        return note
    }

    fun nomenclatureAsFlat() = this.sharpNomenclature.replace("#", "♭")

    companion object {
        fun getRandomNote(): NoteKey {
            return with(NoteKey.entries) {
                val randomIndex: Int = Random().nextInt(size)
                this[randomIndex]
            }
        }

        fun getOrderStartingFromNome(note: NoteKey, length: Int): List<NoteKey> {
            val result = mutableListOf<NoteKey>()
            var currentNote = note
            result.add(currentNote)
            for (i in 0 until length - 1) {
                currentNote = currentNote.getNext()
                result.add(currentNote)
            }
            return result
        }

        fun getRandomOrderedNoteSequence(length: Int): List<NoteKey> {
            val result = mutableListOf<NoteKey>()
            var currentNote = NoteKey.getRandomNote()
            result.add(currentNote)
            for (i in 0 until length - 1) {
                currentNote = currentNote.getNext()
                result.add(currentNote)
            }
            return result
        }
    }
}