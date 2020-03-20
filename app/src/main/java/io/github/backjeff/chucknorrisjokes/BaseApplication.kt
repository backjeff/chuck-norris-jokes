package io.github.backjeff.chucknorrisjokes

import android.app.Application

@Suppress("unused")
class BaseApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        /*
        startKoin {
            modules(
                intentModule +
                        listOf(
                            presentationModule,
                            domainModule,
                            dataModule,
                            dataRemoteModule,
                            dataLocalModule
                        )
            ).androidContext(applicationContext)
        }
        */
    }
}