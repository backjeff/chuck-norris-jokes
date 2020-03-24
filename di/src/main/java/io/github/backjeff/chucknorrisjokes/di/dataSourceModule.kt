package io.github.backjeff.chucknorrisjokes.di

import io.github.backjeff.chucknorrisjokes.data.datasource.local.SettingsDataSource
import io.github.backjeff.chucknorrisjokes.data.datasource.remote.JokeDataSource
import io.github.backjeff.chucknorrisjokes.data_local.datasource.SettingsDataSourceImpl
import io.github.backjeff.chucknorrisjokes.data_local.utils.PreferencesHelper
import io.github.backjeff.chucknorrisjokes.data_remote.datasource.JokeDataSourceImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.bind
import org.koin.dsl.module


val dataSourceModule = module {

    single { PreferencesHelper(androidApplication()) }

    single {
        JokeDataSourceImpl(get())
    } bind JokeDataSource::class

    single {
        SettingsDataSourceImpl(get())
    } bind SettingsDataSource::class

}
