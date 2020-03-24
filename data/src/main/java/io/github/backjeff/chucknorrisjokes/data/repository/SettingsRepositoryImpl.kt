package io.github.backjeff.chucknorrisjokes.data.repository

import io.github.backjeff.chucknorrisjokes.data.datasource.local.SettingsDataSource
import io.github.backjeff.chucknorrisjokes.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single

class SettingsRepositoryImpl(
    private val settingsDataSource: SettingsDataSource
): SettingsRepository {

    override fun getSoundConfig() = flow {
        emit(
            settingsDataSource.getSoundConfig().single()
        )
    }

    override fun setSoundConfig(soundConfig: Boolean) =
        settingsDataSource.setSoundConfig(soundConfig)

}