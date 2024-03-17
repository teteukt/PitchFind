package br.com.teteukt.pitchfind.presentation.game

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.teteukt.pitchfind.domain.FinishResult
import br.com.teteukt.pitchfind.domain.NoteKey
import br.com.teteukt.pitchfind.domain.NoteSequenceGeneratorStrategy
import br.com.teteukt.pitchfind.service.SoundPoolService
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion

class GameViewModel(private val soundPoolService: SoundPoolService) : ViewModel() {

    private val _noteSequence = mutableStateOf(listOf<NoteKey>())
    val noteSequence: State<List<NoteKey>> = _noteSequence

    private val _choices = mutableStateOf(listOf<NoteKey>())
    val choices: State<List<NoteKey>> = _choices

    private val _currentPlayingState = MutableLiveData<PlayingState>(PlayingState.Idle)
    val currentPlayingState: LiveData<PlayingState> = _currentPlayingState

    private val _resultEvent = MutableLiveData<ResultEvent?>(null)
    val resultEvent: LiveData<ResultEvent?> = _resultEvent

    private val _correctNoteKey = mutableStateOf<NoteKey?>(null)
    val correctNoteKey: State<NoteKey?> = _correctNoteKey

    private val _replayCount = mutableIntStateOf(2)
    val replayCount: State<Int> = _replayCount

    private val _canReplay = mutableStateOf(false)
    val canReplay: State<Boolean> = _canReplay

    private val _currentPlayingNoteIndex = mutableIntStateOf(0)
    val currentPlayingNoteIndex: State<Int> = _currentPlayingNoteIndex

    fun stopSounds() {
        soundPoolService.stopAll()
    }

    fun tryChoice(noteKey: NoteKey) {
        if (noteKey == _correctNoteKey.value) {
            _resultEvent.value = ResultEvent.CorrectAnswer
        } else {
            _resultEvent.value = ResultEvent.MissedAnswer
        }
    }

    fun skip() {
        _resultEvent.value = ResultEvent.Skipped
    }

    fun generateAndPlaySequence(strategy: NoteSequenceGeneratorStrategy) {
        generateNoteSequence(strategy)
        playSequence(1000)
    }

    fun replaySequence() {
        playSequence(1000)
        _replayCount.intValue -= 1
    }

    private fun playSequence(
        delay: Long,
    ) {
        _canReplay.value = false
        _currentPlayingState.value = PlayingState.Started
        flow {
            var i = 0;
            delay(delay)
            while (i < _noteSequence.value.size) {
                val currentNote = _noteSequence.value[i]
                playNote(currentNote)
                _currentPlayingState.postValue(PlayingState.Playing)
                _currentPlayingNoteIndex.intValue = i
                i++
                delay(delay)
            }
            emit(Unit)
        }.onCompletion {
            _canReplay.value = true
            _currentPlayingState.postValue(PlayingState.Ended)
            _currentPlayingState.postValue(PlayingState.Idle)
        }.launchIn(viewModelScope)
    }

    private fun playNote(note: NoteKey) {
        soundPoolService.play(NoteKey.A.getRatioFrom(note))
    }

    private fun generateNoteSequence(strategy: NoteSequenceGeneratorStrategy) {
        val strategyResult = strategy.generate()
        _choices.value = strategyResult.choices
        _noteSequence.value = strategyResult.noteSequence
        _correctNoteKey.value = strategyResult.correctNote
    }

    sealed class PlayingState {
        data object Idle : PlayingState()
        data object Started : PlayingState()
        data object Playing : PlayingState()
        data object Ended : PlayingState()
    }

    sealed class ResultEvent(val finishResult: FinishResult) {
        data object Skipped : ResultEvent(FinishResult.SKIPPED)
        data object MissedAnswer : ResultEvent(FinishResult.MISSED)
        data object CorrectAnswer : ResultEvent(FinishResult.CORRECT)
    }
}