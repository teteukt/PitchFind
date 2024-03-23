package br.com.teteukt.pitchfind.presentation.start

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.teteukt.pitchfind.R
import br.com.teteukt.pitchfind.domain.ChallengeMode
import br.com.teteukt.pitchfind.presentation.start.ui.GameModeDropdown
import br.com.teteukt.pitchfind.presentation.theme.DarkTan
import br.com.teteukt.pitchfind.presentation.theme.Flavescent
import br.com.teteukt.pitchfind.presentation.theme.PitchFindTheme

@Composable
fun StartActivityView(
    selectedChallengeMode: ChallengeMode,
    onClickModeOption: (ChallengeMode) -> Unit,
    onClickPlayButton: () -> Unit
) {
    var expendedModeDropdown by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Flavescent, DarkTan),
                    start = Offset.Zero,
                    end = Offset.Infinite,
                )
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Title()
        PlayButton { onClickPlayButton() }
        FooterTitle()
        Spacer(modifier = Modifier.padding(24.dp))
        Text(
            text = "Modo de jogo",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 18.sp, color = Color.White, fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.padding(8.dp))

        GameModeDropdown(
            modifier = Modifier.padding(horizontal = 16.dp),
            challengeMode = selectedChallengeMode
        ) {
            expendedModeDropdown = true
        }

        DropdownMenu(
            expanded = expendedModeDropdown,
            onDismissRequest = { expendedModeDropdown = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            ChallengeMode.entries.map {
                DropdownMenuItem(
                    text = { Text(text = stringResource(id = it.title)) },
                    onClick = {
                        expendedModeDropdown = false
                        onClickModeOption(it)
                    })
            }
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            textAlign = TextAlign.Center,
            text = stringResource(id = selectedChallengeMode.description),
            color = Color.White
        )
    }
}

@Composable
private fun Title() {
    Text(text = "Ache a Nota", color = Color.White, fontSize = 48.sp, fontWeight = FontWeight.Bold)
}

@Composable
private fun FooterTitle() {
    Text(text = "Iniciar", fontSize = 24.sp, color = Color.White)
}

@Composable
private fun PlayButton(onClick: () -> Unit) {
    IconButton(modifier = Modifier.size(144.dp), onClick = { onClick() }) {
        Image(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds,
            painter = painterResource(id = R.drawable.play),
            contentDescription = "play",
            colorFilter = ColorFilter.tint(color = Color.White)
        )
    }
}

@Preview
@Composable
fun StartActivityViewPreview() {
    PitchFindTheme {
        StartActivityView(
            selectedChallengeMode = ChallengeMode.SEQUENTIAL_4,
            onClickModeOption = {}) {}
    }
}