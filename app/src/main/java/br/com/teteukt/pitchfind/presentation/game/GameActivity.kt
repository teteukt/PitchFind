package br.com.teteukt.pitchfind.presentation.game

import android.app.TaskStackBuilder
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import br.com.teteukt.pitchfind.domain.ChallengeMode
import br.com.teteukt.pitchfind.domain.NoteSequenceGeneratorStrategy
import br.com.teteukt.pitchfind.presentation.finished.FinishedActivity
import br.com.teteukt.pitchfind.presentation.game.view.GameActivityView
import br.com.teteukt.pitchfind.presentation.start.StartActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class GameActivity : ComponentActivity() {

    private val viewModel: GameViewModel by viewModel()

    private fun getModeExtra() = intent.extras?.getString("EXTRA_MODE")

    private fun getMode(): ChallengeMode {
        return ChallengeMode.entries.find { result -> result.name == getModeExtra() }
            ?: throw IllegalArgumentException("invalid mode extra")
    }

    private fun getModeNoteSequencerStrategy(): NoteSequenceGeneratorStrategy {
        return when (getMode()) {
            ChallengeMode.SINGLE_NOTE -> NoteSequenceGeneratorStrategy.SingleNote
            ChallengeMode.SEQUENTIAL_4 -> NoteSequenceGeneratorStrategy.MultipleSequentialNotes(4)
            ChallengeMode.SEQUENTIAL_5 -> NoteSequenceGeneratorStrategy.MultipleSequentialNotes(5)
            ChallengeMode.SHUFFLED_4 -> NoteSequenceGeneratorStrategy.MultipleShuffledNotes(4)
            ChallengeMode.SHUFFLED_5 -> NoteSequenceGeneratorStrategy.MultipleShuffledNotes(5)
        }
    }

    private fun startFinishedActivity(resultEvent: GameViewModel.ResultEvent) {
        finish()
        val finishedIntent = Intent(this, FinishedActivity::class.java).run {
            putExtra("EXTRA_MODE", getModeExtra())
            putExtra("extra_finished_result", resultEvent.finishResult.extra)
            putExtra("EXTRA_SCORE", viewModel.getScore())
        }

        val startIntent = Intent(this, StartActivity::class.java)

        TaskStackBuilder.create(this)
            .addNextIntent(startIntent)
            .addNextIntentWithParentStack(finishedIntent)
            .startActivities()
    }

    override fun onPause() {
        super.onPause()
        viewModel.stopSounds()
    }

    override fun onStop() {
        super.onStop()
        viewModel.stopSounds()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            LaunchedEffect(key1 = "init") {
                viewModel.generateAndPlaySequence(getModeNoteSequencerStrategy())
                viewModel.resultEvent.observe(this@GameActivity) {
                    if (it != null) startFinishedActivity(it)
                }
            }

            GameActivityView(
                notes = viewModel.noteSequence.value,
                replayCount = viewModel.replayCount.value,
                enabledReplay = viewModel.canReplay.value,
                choices = viewModel.choices.value,
                showNotesAsFlat = viewModel.showNotesAsFlat.value,
                step = viewModel.currentPlayingNoteIndex.value,
                showReplayAndChoices = viewModel.showReplayAndChoices.value,
                onClickSkip = {
                    viewModel.skip()
                },
                onClickChoice = {
                    viewModel.tryChoice(it)
                },
                onClickReplay = {
                    viewModel.replaySequence()
                })
        }

        onBackPressedDispatcher.addCallback(this@GameActivity, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                startFinishedActivity(GameViewModel.ResultEvent.Skipped)
            }
        })
    }
}