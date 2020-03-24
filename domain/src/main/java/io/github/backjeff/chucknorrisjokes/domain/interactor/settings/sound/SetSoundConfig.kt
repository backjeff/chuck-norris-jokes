package io.github.backjeff.chucknorrisjokes.domain.interactor.settings.sound

import io.github.backjeff.chucknorrisjokes.domain.core.UseCase
import io.github.backjeff.chucknorrisjokes.domain.exception.MissingParamsException
import io.github.backjeff.chucknorrisjokes.domain.repository.SettingsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class SetSoundConfig(
    scope: CoroutineScope,
    private val settingsRepository: SettingsRepository
) : UseCase<Unit, SetSoundConfig.Params>(scope) {

    override fun run(params: Params?): Flow<Unit> = when {
        params == null -> throw MissingParamsException()
        else -> settingsRepository.setSoundConfig(params.soundConfig)
    }

    data class Params (
        val soundConfig: Boolean
    )

}