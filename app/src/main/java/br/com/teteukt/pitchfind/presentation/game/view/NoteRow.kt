package br.com.teteukt.pitchfind.presentation.game.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.teteukt.pitchfind.domain.NoteKey
import br.com.teteukt.pitchfind.presentation.theme.GraniteGray
import br.com.teteukt.pitchfind.presentation.theme.Gray

@Composable
fun NoteRow(notes: List<NoteKey>, showNotesAsFlat: Boolean, step: Int = 0) {
    LazyRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        itemsIndexed(notes) { index, note ->

            fun isCurrentStep() = index == step
            fun isLast() = step == notes.size - 1

            if (index == notes.size - 1) {
                NoteCharacter(note = null, showNotesAsFlat, isLast() || isCurrentStep())
            } else {
                NoteCharacter(note = note, showNotesAsFlat, bold = isCurrentStep())
            }
        }
    }
}

@Composable
fun NoteCharacter(note: NoteKey?, showAsFlat: Boolean, bold: Boolean = false) {

    Text(
        text = note?.let { if(showAsFlat) it.flatNomenclature else it.sharpNomenclature } ?: "?",
        modifier = Modifier.padding(horizontal = 2.dp),
        fontSize = 48.sp,
        color = if (bold) GraniteGray else Gray,
        fontWeight = if (bold) FontWeight.Bold else FontWeight.Normal
    )
}

@Composable
@Preview(showBackground = true)
private fun NoteRowPreview(notes: List<String>) {
    NoteRow(listOf(NoteKey.C, NoteKey.D, NoteKey.E, NoteKey.F), false, 4)
}

@Composable
@Preview(showBackground = true)
private fun FlatNoteRowPreview(notes: List<String>) {
    NoteRow(listOf(NoteKey.C_SHARP, NoteKey.D_SHARP, NoteKey.E, NoteKey.F_SHARP), true, 4)
}