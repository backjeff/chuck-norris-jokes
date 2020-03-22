package io.github.backjeff.chucknorrisjokes.feature_random_joke.splash

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import io.github.backjeff.chucknorrisjokes.base.extensions.asLiveData
import io.github.backjeff.chucknorrisjokes.base.extensions.postSuccess
import io.github.backjeff.chucknorrisjokes.base.extensions.viewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent

class SplashViewModel(app: Application) : AndroidViewModel(app), KoinComponent {

    private val _blinkViewState by viewState<Boolean>()
    private val _splashViewState by viewState<Unit>()
    val splashViewState = _splashViewState.asLiveData()
    val blinkViewState = _blinkViewState.asLiveData()

    init {
        startCounter()
    }

    fun startCounter() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(500)
            _blinkViewState.postSuccess(true)
            delay(100)
            _blinkViewState.postSuccess(false)
            delay(500)
            _blinkViewState.postSuccess(true)
            delay(100)
            _blinkViewState.postSuccess(false)
            delay(1000)
            _splashViewState.postSuccess(Unit)
        }
    }

}
