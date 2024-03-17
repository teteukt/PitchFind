package br.com.teteukt.pitchfind.presentation.game.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.teteukt.pitchfind.domain.NoteKey
import br.com.teteukt.pitchfind.presentation.theme.DarkTan
import br.com.teteukt.pitchfind.presentation.theme.Flavescent
import br.com.teteukt.pitchfind.presentation.theme.GraniteGray

@Composable
fun ChoiceButton(noteKey: NoteKey, onClick: () -> Unit) {
    ElevatedButton(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp),
        onClick = { onClick() },
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.secondary),
        colors = ButtonDefaults.elevatedButtonColors(containerColor = MaterialTheme.colorScheme.primary),
        elevation = ButtonDefaults.elevatedButtonElevation(),
        contentPadding = PaddingValues()
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Flavescent, DarkTan),
                        start = Offset.Zero,
                        end = Offset.Infinite,
                    )
                ),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = noteKey.nomenclature,
                color = GraniteGray,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(start = 24.dp)
            )
        }
    }
}

@Composable
@Preview
private fun ChoiceButtonPreview() {
    ChoiceButton(NoteKey.A) {}
}