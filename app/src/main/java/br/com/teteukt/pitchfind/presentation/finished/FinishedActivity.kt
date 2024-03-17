package br.com.teteukt.pitchfind.presentation.finished

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.teteukt.pitchfind.domain.FinishResult
import br.com.teteukt.pitchfind.presentation.game.GameActivity
import br.com.teteukt.pitchfind.presentation.theme.PitchFindTheme
import kotlin.math.absoluteValue


class FinishedActivity : ComponentActivity() {

    private fun getResultExtra() = intent.extras?.getString("extra_finished_result")

    private fun getFinishResult(): FinishResult {
        return FinishResult.entries.find { result -> result.extra == getResultExtra() }
            ?: throw IllegalArgumentException("invalid result extra")
    }

    private fun startGame() {
        finish()
        startActivity(Intent(this@FinishedActivity, GameActivity::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FinishedActivityView(getFinishResult()) {
                startGame()
            }
        }
    }
}

@Composable
fun FinishedActivityView(finishResult: FinishResult, onClickNext: () -> Unit) {
    PitchFindTheme {
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
                    id = finishResult.pointChange,
                    finishResult.pointChangeAmount.absoluteValue
                )
            Text(text = pointChangeLabel, fontSize = 24.sp, color = Color.White, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.padding(vertical = 12.dp))
            OptionButton("Próximo") {
                onClickNext()
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SkippedFinishedActivityPreview() {
    FinishedActivityView(FinishResult.SKIPPED) {}
}

@Composable
@Preview(showBackground = true)
fun CorrectFinishedActivityPreview() {
    FinishedActivityView(FinishResult.CORRECT) {}
}

@Composable
@Preview(showBackground = true)
fun MissedFinishedActivityPreview() {
    FinishedActivityView(FinishResult.MISSED) {}
}