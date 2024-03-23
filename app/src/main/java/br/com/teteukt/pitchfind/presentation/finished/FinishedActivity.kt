package br.com.teteukt.pitchfind.presentation.finished

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.teteukt.pitchfind.domain.FinishResult
import br.com.teteukt.pitchfind.presentation.game.GameActivity
import br.com.teteukt.pitchfind.presentation.game.view.OutlineChoiceButton
import br.com.teteukt.pitchfind.presentation.start.StartActivity
import br.com.teteukt.pitchfind.presentation.theme.PitchFindTheme
import kotlin.math.absoluteValue


class FinishedActivity : ComponentActivity() {

    private fun getResultExtra() = intent.extras?.getString("extra_finished_result")
    private fun getModeExtra() = intent.extras?.getString("EXTRA_MODE")
    private fun getScoreExtra() = intent.extras?.getInt("EXTRA_SCORE") ?: 0

    private fun getFinishResult(): FinishResult {
        return FinishResult.entries.find { result -> result.extra == getResultExtra() }
            ?: throw IllegalArgumentException("invalid result extra")
    }

    private fun startGame() {
        finish()
        Intent(this@FinishedActivity, GameActivity::class.java).apply {
            putExtra("EXTRA_MODE", getModeExtra())
        }.run { startActivity(this) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var canStartScoreAnimation by remember {
                mutableStateOf(false)
            }

            var canStartScoreFadeAwayAnimation by remember {
                mutableStateOf(false)
            }

            val scoreChange by animateIntAsState(targetValue = if (canStartScoreAnimation) getFinishResult().scoreChangeAmount else 0,
                label = "ScoreChangeInt",
                animationSpec = tween(1000),
                finishedListener = {
                    canStartScoreFadeAwayAnimation = true
                }
            )

            val scoreFadeAway by animateFloatAsState(
                targetValue = if (canStartScoreFadeAwayAnimation) 1.0F else 0.0F,
                label = "ScoreFadeAwayFloat"
            )

            LaunchedEffect(key1 = "init") {
                canStartScoreAnimation = true
            }


            FinishedActivityView(
                getFinishResult(),
                scoreFadeAway,
                scoreChange,
                getScoreExtra() + scoreChange, onClickNext = {
                    startGame()
                }, onClickExit = {
                    finish()
                })
        }
    }
}

@Composable
fun FinishedActivityView(
    finishResult: FinishResult,
    scoreChangeFadeProgress: Float = 0F,
    scoreChange: Int,
    scoreBeforeChange: Int,
    onClickNext: () -> Unit,
    onClickExit: () -> Unit,
) {
    PitchFindTheme(statusBarColor = finishResult.color) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = finishResult.color)
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(id = finishResult.label),
                fontSize = 24.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.padding(vertical = 12.dp))
            Icon(
                modifier = Modifier.size(144.dp),
                painter = painterResource(id = finishResult.icon),
                contentDescription = "pulou",
                tint = Color.White
            )
            Spacer(modifier = Modifier.padding(vertical = 12.dp))
            val pointChangeLabel =
                stringResource(
                    id = finishResult.scoreChangeLabel,
                    finishResult.scoreChangeAmount.absoluteValue
                )
            Text(text = pointChangeLabel, fontSize = 24.sp, color = Color.White, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.padding(vertical = 12.dp))
            if(scoreChangeFadeProgress < 1.0) {
                Text(
                    modifier = Modifier
                        .offset(y = (scoreChangeFadeProgress * 40).dp)
                        .alpha(1 - scoreChangeFadeProgress),
                    text = scoreChange.toString(),
                    fontSize = 24.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Normal
                )
            }
            Text(
                text = scoreBeforeChange.toString(),
                fontSize = 32.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.padding(vertical = 32.dp))
            OptionButton("Próximo") {
                onClickNext()
            }
            Spacer(modifier = Modifier.padding(vertical = 4.dp))
            OutlinedOptionButton("Sair") {
                onClickExit()
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SkippedFinishedActivityPreview() {
    FinishedActivityView(FinishResult.SKIPPED, 0F, -400, 1000, {}) {}
}

@Composable
@Preview(showBackground = true)
fun CorrectFinishedActivityPreview() {
    FinishedActivityView(FinishResult.CORRECT, 0F, 500, 1000, {}) {}
}

@Composable
@Preview(showBackground = true)
fun MissedFinishedActivityPreview() {
    FinishedActivityView(FinishResult.MISSED, 0F, -300, 1000, {}) {}
}