package br.com.teteukt.pitchfind.domain

data class Challenge(
    val timesPlayerCanPlayHiddenNote: Int? = null,
    val timer: Int? = null,
    val showAsFlat: Boolean = false,
    val options: List<NoteKey>,
    val noteArrange: ChallengeNoteArrange
)