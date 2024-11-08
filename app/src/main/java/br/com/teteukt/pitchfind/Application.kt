package br.com.teteukt.pitchfind

import android.app.Application
import br.com.teteukt.pitchfind.di.appModules
import br.com.teteukt.pitchfind.domain.NoteKey
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Application)
            modules(appModules)
        }
    }
}