package io.github.backjeff.chucknorrisjokes.base.core

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.github.backjeff.chucknorrisjokes.base.extensions.*
import io.github.backjeff.chucknorrisjokes.domain.interactor.settings.sound.GetSoundConfig
import io.github.backjeff.chucknorrisjokes.domain.interactor.settings.sound.SetSoundConfig
import org.koin.core.KoinComponent

open class BaseViewModel(app: Application) : AndroidViewModel(app), KoinComponent {

    private val _soundConfigViewState by viewState<Boolean>()
    val soundConfigState = _soundConfigViewState.asLiveData()

    private val _toggleSoundConfigViewState by viewState<Boolean>()
    val toggleSoundConfigState = _toggleSoundConfigViewState.asLiveData()

    val getSoundConfig: GetSoundConfig by useCase()
    val setSoundConfig: SetSoundConfig by useCase()

    init {
        getSoundConfig()
    }

    fun getSoundConfig() {
        getSoundConfig(
            onSuccess = {
                soundConfig = it
                _soundConfigViewState.postSuccess(it)
            },
            onError = {
                soundConfig = true
                _soundConfigViewState.postSuccess(true)
            }
        )
    }

    fun toggleSound() {
        val soundEnable = soundConfig?.let { !it } ?: false
        setSoundConfig(
            params = SetSoundConfig.Params(
                soundConfig = soundEnable
            ),
            onSuccess = {
                soundConfig = soundEnable
                _toggleSoundConfigViewState.postSuccess(soundEnable)
            },
            onError = {
                _toggleSoundConfigViewState.postSuccess(false)
            }
        )
    }

    companion object {
        var soundConfig: Boolean? = null
    }

    open fun clearStates() {
        _soundConfigViewState.postNeutral()
    }

}
