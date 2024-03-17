package br.com.teteukt.pitchfind.domain

import br.com.teteukt.pitchfind.extensions.getRandom

sealed class NoteSequenceGeneratorStrategy {
    data object SingleNote : NoteSequenceGeneratorStrategy() {
        override fun generate(): Result {
            val randomNote = NoteKey.getRandomNote()
            val orderedNoteSequence = NoteKey.getOrderStartingFromNome(randomNote, 4).shuffled()
            return Result(
                noteSequence = listOf(randomNote),
                correctNote = randomNote,
                choices = orderedNoteSequence
            )
        }
    }

    data class MultipleSequentialNotes(private val length: Int) : NoteSequenceGeneratorStrategy() {
        override fun generate(): Result {
            if(length <= 1) throw IllegalStateException("Use NoteSequenceGeneratorStrategy.SingleNote")
            val generatedOrderedNoteSequence = NoteKey.getRandomOrderedNoteSequence(length)
            val correctNote = generatedOrderedNoteSequence.getRandom()
            return Result(
                noteSequence = generatedOrderedNoteSequence + listOf(correctNote),
                correctNote = correctNote,
                choices = generatedOrderedNoteSequence.shuffled()
            )
        }
    }

    data class MultipleShuffledNotes(private val length: Int) : NoteSequenceGeneratorStrategy() {
        override fun generate(): Result {
            if(length <= 1) throw IllegalStateException("Use NoteSequenceGeneratorStrategy.SingleNote")
            val generatedOrderedNoteSequence = NoteKey.getRandomOrderedNoteSequence(length).shuffled()
            val correctNote = generatedOrderedNoteSequence.getRandom()
            return Result(
                noteSequence = generatedOrderedNoteSequence + listOf(correctNote),
                correctNote = correctNote,
                choices = generatedOrderedNoteSequence.shuffled()
            )
        }
    }

    abstract fun generate(): Result

    data class Result(
        val noteSequence: List<NoteKey>,
        val correctNote: NoteKey,
        val choices: List<NoteKey>
    )
}