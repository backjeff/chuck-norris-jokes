package io.github.backjeff.chucknorrisjokes.feature_random_joke.random_joke

import android.app.Application
import io.github.backjeff.chucknorrisjokes.base.extensions.*
import io.github.backjeff.chucknorrisjokes.domain.interactor.joke.GetRandomJoke
import io.github.backjeff.chucknorrisjokes.domain.model.Joke
import io.github.backjeff.chucknorrisjokes.feature_random_joke.BaseRandomJokeViewModel
import org.koin.core.KoinComponent

class RandomJokeViewModel(app: Application) : BaseRandomJokeViewModel(app), KoinComponent {

    private val _randomJokeViewState by viewState<Joke>()
    val randomJokeViewState = _randomJokeViewState.asLiveData()

    private val _blinkViewState by viewState<Boolean>()
    val blinkViewState = _blinkViewState.asLiveData()

    private val getRandomJoke: GetRandomJoke by useCase()

    init { }

    fun getJokeCategoryText() = selectedCategory

    fun requestRandomJoke() {
        _randomJokeViewState.postLoading()
        getRandomJoke(
            params = GetRandomJoke.Params(
                categoryId = selectedCategory
            ),
            onSuccess = {
                _randomJokeViewState.postSuccess(it)
                blink()
            },
            onError = {
                _randomJokeViewState.postError(it)
            }
        )
    }

    fun blink() = _blinkViewState.postSuccess(soundConfig ?: true)

    override fun clearStates() {
        super.clearStates()
        _blinkViewState.postNeutral()
    }

}
