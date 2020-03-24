package io.github.backjeff.chucknorrisjokes.data.datasource.local

import kotlinx.coroutines.flow.Flow

interface SettingsDataSource {
    fun getSoundConfig(): Flow<Boolean>
    fun setSoundConfig(soundConfig: Boolean): Flow<Unit>
}