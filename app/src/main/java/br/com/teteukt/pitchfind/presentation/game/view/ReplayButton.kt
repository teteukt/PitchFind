package br.com.teteukt.pitchfind.presentation.game.view

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import br.com.teteukt.pitchfind.R
import br.com.teteukt.pitchfind.presentation.theme.GraniteGray
import br.com.teteukt.pitchfind.presentation.theme.Gray

@Composable
fun ReplayButton(count: Int, enabled: Boolean = true, onClick: () -> Unit) {
    val disabled = !enabled || count == 0
    val color = if (disabled) Gray else GraniteGray

    Row(verticalAlignment = Alignment.CenterVertically) {
        IconButton(enabled = !disabled, onClick = { onClick() }) {
            Icon(
                painter = painterResource(id = R.drawable.play),
                contentDescription = "replay",
                tint = color
            )
        }

        Text(text = "x$count", fontWeight = FontWeight.Bold, color = color, fontSize = 16.sp)
    }
}

@Composable
@Preview(showBackground = true)
fun ReplayButtonPreview() {
    ReplayButton(2) {}
}