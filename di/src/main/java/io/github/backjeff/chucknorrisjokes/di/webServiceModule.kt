package io.github.backjeff.chucknorrisjokes.di

import io.github.backjeff.chucknorrisjokes.data_remote.service.JokeWebService
import io.github.backjeff.chucknorrisjokes.data_remote.util.BASE_URL
import io.github.backjeff.chucknorrisjokes.data_remote.util.WebServiceFactory
import org.koin.dsl.bind
import org.koin.dsl.module


val webServiceModule = module {

    single {
        WebServiceFactory.createWebService(
            okHttpClient = get(),
            url = BASE_URL
        ) as JokeWebService
    } bind JokeWebService::class

}
