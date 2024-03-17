package br.com.teteukt.pitchfind.domain

sealed class ChallengeNoteArrange {
    class SingleNote(val note: NoteKey) : ChallengeNoteArrange()
    class SequentialNote(val notes: List<NoteKey>) : ChallengeNoteArrange()
}