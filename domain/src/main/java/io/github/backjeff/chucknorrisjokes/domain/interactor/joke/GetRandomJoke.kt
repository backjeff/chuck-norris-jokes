package io.github.backjeff.chucknorrisjokes.domain.interactor.joke

import io.github.backjeff.chucknorrisjokes.domain.core.UseCase
import io.github.backjeff.chucknorrisjokes.domain.exception.MissingParamsException
import io.github.backjeff.chucknorrisjokes.domain.model.Joke
import io.github.backjeff.chucknorrisjokes.domain.repository.JokeRepository
import kotlinx.coroutines.CoroutineScope

class GetRandomJoke(
    scope: CoroutineScope,
    private val jokeRepository: JokeRepository
) : UseCase<Joke, GetRandomJoke.Params>(scope) {

    override fun run(params: Params?) = when {
        params == null -> throw MissingParamsException()
        else -> jokeRepository.getRandomJoke(params.categoryId)
    }

    data class Params(
        val categoryId: Int?
    )

}