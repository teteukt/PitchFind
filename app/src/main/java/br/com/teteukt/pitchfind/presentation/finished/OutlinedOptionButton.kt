package br.com.teteukt.pitchfind.presentation.finished

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.teteukt.pitchfind.R
import br.com.teteukt.pitchfind.presentation.theme.PitchFindTheme

@Composable
fun OutlinedOptionButton(text: String, onClick: () -> Unit) {
    OutlinedButton(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp),
        onClick = { onClick() },
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, color = Color.White),
        contentPadding = PaddingValues()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = text,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(start = 24.dp)
            )

            Icon(
                modifier = Modifier.padding(end = 24.dp),
                painter = painterResource(id = R.drawable.arrow_right),
                contentDescription = "skip",
                tint = Color.White
            )
        }
    }
}

@Composable
@Preview
fun OutlinedOptionButtonPreview() {
    PitchFindTheme {
        OutlinedOptionButton("Texto") {}
    }
}