package br.com.teteukt.pitchfind.domain

import androidx.annotation.StringRes
import br.com.teteukt.pitchfind.R

enum class ChallengeMode(@StringRes val title: Int, @StringRes val description: Int) {
    SINGLE_NOTE(
        R.string.mode_single_note,
        R.string.mode_single_note_description
    ),
    SEQUENTIAL_4(R.string.mode_four_sequential, R.string.mode_four_sequential_description),
    SHUFFLED_4(R.string.mode_four_shuffled, R.string.mode_four_shuffled_description),
    SEQUENTIAL_5(R.string.mode_five_sequential, R.string.mode_five_sequential_description),
    SHUFFLED_5(R.string.mode_five_shuffled, R.string.mode_five_shuffled_description),
}