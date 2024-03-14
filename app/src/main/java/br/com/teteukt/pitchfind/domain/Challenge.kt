package br.com.teteukt.pitchfind.domain

import br.com.teteukt.pitchfind.NoteKey

data class Challenge(
    val timesPlayerCanPlayHiddenNote: Int? = null,
    val timer: Int? = null,
    val showAsFlat: Boolean = false,
    val options: List<NoteKey>,
    val noteArrange: ChallengeNoteArrange
)