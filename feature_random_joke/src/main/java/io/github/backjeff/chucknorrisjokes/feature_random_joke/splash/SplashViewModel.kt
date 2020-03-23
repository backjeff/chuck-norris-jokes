package io.github.backjeff.chucknorrisjokes.feature_random_joke.splash

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.github.backjeff.chucknorrisjokes.base.extensions.asLiveData
import io.github.backjeff.chucknorrisjokes.base.extensions.postSuccess
import io.github.backjeff.chucknorrisjokes.base.extensions.viewState
import org.koin.core.KoinComponent

class SplashViewModel(app: Application) : AndroidViewModel(app), KoinComponent {

    private val _blinkViewState by viewState<Unit>()
    val blinkViewState = _blinkViewState.asLiveData()

    init {
        startCounter()
    }

    private fun startCounter() = _blinkViewState.postSuccess(Unit)

}
