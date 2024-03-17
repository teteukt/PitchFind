package br.com.teteukt.pitchfind.presentation.start

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.teteukt.pitchfind.R
import br.com.teteukt.pitchfind.presentation.game.GameActivity
import br.com.teteukt.pitchfind.presentation.theme.PitchFindTheme

class StartActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PitchFindTheme {
                StartActivityView {
                    startActivity(Intent(this@StartActivity, GameActivity::class.java))
                }
            }
        }
    }
}