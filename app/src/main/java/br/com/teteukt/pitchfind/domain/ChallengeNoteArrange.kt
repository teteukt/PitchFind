package br.com.teteukt.pitchfind.domain

import br.com.teteukt.pitchfind.NoteKey

sealed class ChallengeNoteArrange {
    class SingleNote(val note: NoteKey) : ChallengeNoteArrange()
    class SequentialNote(val notes: List<NoteKey>) : ChallengeNoteArrange()
}