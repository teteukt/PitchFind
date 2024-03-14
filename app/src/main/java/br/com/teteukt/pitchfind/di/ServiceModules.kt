package br.com.teteukt.pitchfind.di

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.SoundPool
import br.com.teteukt.pitchfind.R
import br.com.teteukt.pitchfind.service.SoundPoolService
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val serviceModules = module {
    single { MediaPlayer.create(androidContext(), R.raw.piano_a_key_440) }
    single {
        SoundPool.Builder()
            .setMaxStreams(10)
            .setAudioAttributes(
                AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_GAME).build()
            )
            .build()
    }
    single { SoundPoolService(androidContext(), get()) }
}