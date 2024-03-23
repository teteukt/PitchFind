package br.com.teteukt.pitchfind.presentation.start

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import br.com.teteukt.pitchfind.domain.ChallengeMode
import br.com.teteukt.pitchfind.presentation.game.GameActivity
import br.com.teteukt.pitchfind.presentation.theme.PitchFindTheme

class StartActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var selectedChallengeMode by remember {
                mutableStateOf(ChallengeMode.SEQUENTIAL_4)
            }

            PitchFindTheme {
                StartActivityView(
                    selectedChallengeMode = selectedChallengeMode,
                    onClickModeOption = {
                        selectedChallengeMode = it
                    }) {
                    Intent(this@StartActivity, GameActivity::class.java).apply {
                        putExtra("EXTRA_MODE", selectedChallengeMode.name)
                    }.run { startActivity(this) }
                }
            }
        }
    }
}