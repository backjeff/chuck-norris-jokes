package io.github.backjeff.chucknorrisjokes.di

import io.github.backjeff.chucknorrisjokes.data.repository.JokeRepositoryImpl
import io.github.backjeff.chucknorrisjokes.domain.repository.JokeRepository
import org.koin.dsl.bind
import org.koin.dsl.module


val dataModule = module {

    single {
        JokeRepositoryImpl(get())
    } bind JokeRepository::class

}
