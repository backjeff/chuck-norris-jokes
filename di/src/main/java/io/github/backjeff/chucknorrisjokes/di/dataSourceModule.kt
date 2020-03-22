package io.github.backjeff.chucknorrisjokes.di

import io.github.backjeff.chucknorrisjokes.data.datasource.remote.JokeDataSource
import io.github.backjeff.chucknorrisjokes.data_remote.datasource.JokeDataSourceImpl
import org.koin.dsl.bind
import org.koin.dsl.module


val dataSourceModule = module {

    single {
        JokeDataSourceImpl(get())
    } bind JokeDataSource::class

}
