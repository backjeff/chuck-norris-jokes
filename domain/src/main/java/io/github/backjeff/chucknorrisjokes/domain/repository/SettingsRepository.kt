package io.github.backjeff.chucknorrisjokes.domain.repository

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    fun getSoundConfig(): Flow<Boolean>
    fun setSoundConfig(soundConfig: Boolean): Flow<Unit>
}