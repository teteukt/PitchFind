package br.com.teteukt.pitchfind.di

import br.com.teteukt.pitchfind.presentation.game.GameViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModules = module {
    viewModel { GameViewModel(get()) }
}