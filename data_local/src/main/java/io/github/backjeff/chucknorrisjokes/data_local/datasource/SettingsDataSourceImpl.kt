package io.github.backjeff.chucknorrisjokes.data_local.datasource

import io.github.backjeff.chucknorrisjokes.data.datasource.local.SettingsDataSource
import io.github.backjeff.chucknorrisjokes.data_local.utils.PreferencesHelper
import kotlinx.coroutines.flow.flow

class SettingsDataSourceImpl(
    private val preferencesHelper: PreferencesHelper
): SettingsDataSource {

    override fun getSoundConfig() = flow {
        emit(
            preferencesHelper.getBoolean(SOUND_CONFIG)
        )
    }

    override fun setSoundConfig(soundConfig: Boolean) = flow {
        emit(
            preferencesHelper.saveBoolean(SOUND_CONFIG, soundConfig)
        )
    }

    companion object {
        const val SOUND_CONFIG = "sound"
    }

}