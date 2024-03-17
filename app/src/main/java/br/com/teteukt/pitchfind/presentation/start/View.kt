package br.com.teteukt.pitchfind.presentation.start

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.teteukt.pitchfind.R
import br.com.teteukt.pitchfind.presentation.theme.DarkTan
import br.com.teteukt.pitchfind.presentation.theme.Flavescent
import br.com.teteukt.pitchfind.presentation.theme.PitchFindTheme

@Composable
fun StartActivityView(onClickPlayButton: () -> Unit) {
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
    }
}

@Composable
private fun Title() {
    Text(text = "Pitch Find", color = Color.White, fontSize = 48.sp, fontWeight = FontWeight.Bold)
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
        StartActivityView {}
    }
}