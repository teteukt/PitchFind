package br.com.teteukt.pitchfind.presentation.start.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.teteukt.pitchfind.R
import br.com.teteukt.pitchfind.domain.ChallengeMode
import br.com.teteukt.pitchfind.extensions.clickableWithoutRipple
import br.com.teteukt.pitchfind.presentation.theme.NoRippleTheme
import br.com.teteukt.pitchfind.presentation.theme.PitchFindTheme

@Composable
fun GameModeDropdown(
    modifier: Modifier = Modifier,
    challengeMode: ChallengeMode,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        modifier = Modifier
            .then(modifier)
            .clickableWithoutRipple(interactionSource = interactionSource) {
                onClick()
            }
            .fillMaxWidth()
            .height(48.dp)
            .border(
                width = 1.dp,
                color = Color.White,
                shape = RoundedCornerShape(8.dp)
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(id = challengeMode.title),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.padding(start = 24.dp)
        )
        Icon(
            painter = painterResource(id = R.drawable.dropdown_arrow_down),
            contentDescription = "mode dropdown arrow",
            tint = Color.White,
            modifier = Modifier.padding(end = 24.dp)
        )
    }
}

@Composable
@Preview
private fun PreviewGameModeDropdown() {
    PitchFindTheme {
        GameModeDropdown(challengeMode = ChallengeMode.SEQUENTIAL_4) { }
    }
}