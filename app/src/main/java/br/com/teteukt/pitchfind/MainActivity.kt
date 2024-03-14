package br.com.teteukt.pitchfind

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.teteukt.pitchfind.extensions.getRandom
import br.com.teteukt.pitchfind.ui.theme.PitchFindTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PitchFindTheme {
                var options: List<NoteKey> by remember { mutableStateOf(emptyList()) }
                var correctChoice: NoteKey? by remember {
                    mutableStateOf(null)
                }
                var guessedRight by remember {
                    mutableStateOf(false)
                }
                var playChoiceCount by remember {
                    mutableIntStateOf(0)
                }

                var score by remember {
                    mutableIntStateOf(1000)
                }

                val maxPlayChoiceCount = 2

                fun getRandomSequence() {
                    val maxChoices = 5
                    val choices = mutableListOf<NoteKey>()

                    repeat(maxChoices) {
                        choices.add(NoteKey.entries.filter { choices.contains(it).not() }
                            .getRandom())
                    }

                    options = choices
                    correctChoice = choices.getRandom()
                    guessedRight = false
                    playChoiceCount = 0
                }

                if (correctChoice != null) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val choice = correctChoice ?: return@Column
                        val hiddenNode =
                            if (guessedRight) choice.nomenclature else "?"
                        Row(
                            modifier = Modifier.border(
                                1.dp,
                                color = MaterialTheme.colorScheme.primary
                            ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = hiddenNode,
                                modifier = Modifier.padding(16.dp),
                                color = MaterialTheme.colorScheme.primary
                            )

                            PlayButton(enabled = playChoiceCount < maxPlayChoiceCount) {
                                viewModel.playNote(choice)
                                score -= 100 * playChoiceCount
                                playChoiceCount++
                            }
                        }
                        Spacer(modifier = Modifier.padding(24.dp))
                        OptionList(
                            notes = options,
                            onClickPlayItem = {
                                viewModel.playNote(it)
                                score -= 50
                            }, onClickItem = {
                                if (it == correctChoice) {
                                    guessedRight = true
                                    score += 500
                                } else {
                                    score -= 250
                                }
                            })
                        AnimatedVisibility(visible = guessedRight) {
                            TextButton(onClick = { getRandomSequence() }) {
                                Text(
                                    text = "Próximo",
                                    fontSize = 16.sp,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "P $score", fontSize = 24.sp,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                } else {
                    Button(onClick = { getRandomSequence() }) {
                        Text(text = "Iniciar")
                    }
                }
            }
        }
    }
}

@Composable
fun PlayButton(enabled: Boolean = true, onClick: (() -> Unit)? = null) {
    IconButton(enabled = enabled, onClick = { onClick?.invoke() }) {
        Icon(
            painter = painterResource(id = R.drawable.round_play_circle_outline_24),
            contentDescription = ""
        )
    }
}

@Composable
fun OptionList(
    notes: List<NoteKey>,
    onClickItem: ((NoteKey) -> Unit)? = null,
    onClickPlayItem: ((NoteKey) -> Unit)? = null
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(notes) {
            Button(modifier = Modifier.fillMaxWidth(), onClick = { onClickItem?.invoke(it) }) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = it.nomenclature)
                    PlayButton(onClick = { onClickPlayItem?.invoke(it) })
                }
            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    PitchFindTheme {
        OptionList(notes = listOf(NoteKey.C, NoteKey.A, NoteKey.B, NoteKey.D))
    }
}