package io.github.backjeff.chucknorrisjokes.domain.interactor.settings.sound

import io.github.backjeff.chucknorrisjokes.domain.core.UseCase
import io.github.backjeff.chucknorrisjokes.domain.repository.SettingsRepository
import kotlinx.coroutines.CoroutineScope

class GetSoundConfig(
    scope: CoroutineScope,
    private val settingsRepository: SettingsRepository
) : UseCase<Boolean, Unit>(scope) {

    override fun run(params: Unit?) = settingsRepository.getSoundConfig()

}