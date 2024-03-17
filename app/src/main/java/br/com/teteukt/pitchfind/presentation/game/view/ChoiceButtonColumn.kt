package br.com.teteukt.pitchfind.presentation.game.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.teteukt.pitchfind.domain.NoteKey
import br.com.teteukt.pitchfind.presentation.theme.PitchFindTheme

@Composable
fun ChoiceButtonColumn(
    choices: List<NoteKey>,
    showSkipButton: Boolean = true,
    onClickChoice: (NoteKey) -> Unit,
    onClickSkip: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        choices.map { ChoiceButton(it) { onClickChoice(it) } }

        if (showSkipButton)
            OutlineChoiceButton(text = "Pular") { onClickSkip() }
    }
}

@Composable
@Preview
private fun ChoiceButtonColumnPreview() {
    PitchFindTheme {
        ChoiceButtonColumn(
            listOf(NoteKey.C, NoteKey.D, NoteKey.E, NoteKey.F),
            showSkipButton = true,
            onClickChoice = {},
            onClickSkip = {})
    }
}