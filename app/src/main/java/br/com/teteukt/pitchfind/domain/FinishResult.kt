package br.com.teteukt.pitchfind.domain

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import br.com.teteukt.pitchfind.R
import br.com.teteukt.pitchfind.presentation.theme.FireOpal
import br.com.teteukt.pitchfind.presentation.theme.Maize
import br.com.teteukt.pitchfind.presentation.theme.ScreamingGreen

enum class FinishResult(
    val extra: String,
    val color: Color,
    @DrawableRes val icon: Int,
    @StringRes val label: Int,
    val scoreChangeAmount: Int,
    @StringRes val scoreChangeLabel: Int
) {
    SKIPPED(
        "result_skipped",
        Maize,
        R.drawable.arrow_right,
        R.string.finish_label_result_skipped,
        -300,
        R.string.finish_lost_points_label
    ),
    CORRECT(
        "result_correct",
        ScreamingGreen,
        R.drawable.verified,
        R.string.finish_label_result_correct,
        500,
        R.string.finish_earn_points_label
    ),
    MISSED(
        "result_missed",
        FireOpal,
        R.drawable.close,
        R.string.finish_label_result_missed,
        -400,
        R.string.finish_lost_points_label
    )
}