package io.github.backjeff.chucknorrisjokes.di

import io.github.backjeff.chucknorrisjokes.domain.interactor.joke.GetJokeCategories
import io.github.backjeff.chucknorrisjokes.domain.interactor.joke.GetRandomJoke
import io.github.backjeff.chucknorrisjokes.domain.interactor.joke.SearchJoke
import io.github.backjeff.chucknorrisjokes.domain.interactor.settings.sound.GetSoundConfig
import io.github.backjeff.chucknorrisjokes.domain.interactor.settings.sound.SetSoundConfig
import kotlinx.coroutines.CoroutineScope
import org.koin.dsl.module


val useCaseModule = module {

    factory { (scope: CoroutineScope) ->
        GetRandomJoke(
            scope = scope,
            jokeRepository = get()
        )
    }

    factory { (scope: CoroutineScope) ->
        GetJokeCategories(
            scope = scope,
            jokeRepository = get()
        )
    }

    factory { (scope: CoroutineScope) ->
        SearchJoke(
            scope = scope,
            jokeRepository = get()
        )
    }

    factory { (scope: CoroutineScope) ->
        GetSoundConfig(
            scope = scope,
            settingsRepository = get()
        )
    }

    factory { (scope: CoroutineScope) ->
        SetSoundConfig(
            scope = scope,
            settingsRepository = get()
        )
    }

}
