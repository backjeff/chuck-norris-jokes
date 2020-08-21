package io.github.backjeff.chucknorrisjokes

import android.app.Application
import android.content.Intent
import android.content.IntentSender
import android.os.Bundle
import android.util.Log
import com.google.android.play.core.common.IntentSenderForResultStarter
import io.github.backjeff.chucknorrisjokes.di.*
import io.github.backjeff.chucknorrisjokes.di.intent.intentModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@Suppress("unused")
class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                intentModule +
                        listOf(
                            dataModule,
                            dataSourceModule,
                            domainModule,
                            useCaseModule,
                            viewModelModule,
                            webServiceModule
                        )
            ).androidContext(applicationContext)
        }
    }
}
