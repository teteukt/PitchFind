package br.com.teteukt.pitchfind.presentation.game.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.teteukt.pitchfind.domain.NoteKey
import br.com.teteukt.pitchfind.presentation.theme.GraniteGray
import br.com.teteukt.pitchfind.presentation.theme.PitchFindTheme

@Composable
fun GameActivityView(
    notes: List<NoteKey>,
    replayCount: Int,
    enabledReplay: Boolean,
    choices: List<NoteKey>,
    step: Int,
    onClickReplay: () -> Unit,
    onClickChoice: (NoteKey) -> Unit,
    onClickSkip: () -> Unit,
) {
    PitchFindTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NoteRow(notes, step)
            ReplayButton(count = replayCount, enabled = enabledReplay) { onClickReplay() }
            Spacer(modifier = Modifier.padding(32.dp))
            Text(
                text = "Qual a nota escondida?",
                modifier = Modifier.padding(vertical = 16.dp),
                color = GraniteGray
            )
            ChoiceButtonColumn(
                choices = choices,
                onClickChoice = { onClickChoice(it) },
                onClickSkip = { onClickSkip() })
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun GameActivityViewPreview() {
    GameActivityView(
        notes = listOf(NoteKey.C, NoteKey.D, NoteKey.E, NoteKey.F),
        replayCount = 2,
        choices = listOf(NoteKey.C, NoteKey.D, NoteKey.E, NoteKey.F),
        enabledReplay = true,
        step = 0,
        onClickReplay = {},
        onClickChoice = {},
        onClickSkip = {},
    )
}