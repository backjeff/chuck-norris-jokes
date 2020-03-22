package io.github.backjeff.chucknorrisjokes.di

import io.github.backjeff.chucknorrisjokes.domain.core.ThreadContextProvider
import org.koin.dsl.module


val domainModule = module {

    single {
        ThreadContextProvider()
    }

}
