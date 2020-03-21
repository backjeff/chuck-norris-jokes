package io.github.backjeff.chucknorrisjokes.domain.interactor.joke

import io.github.backjeff.chucknorrisjokes.domain.core.UseCase
import io.github.backjeff.chucknorrisjokes.domain.exception.EmptyFieldException
import io.github.backjeff.chucknorrisjokes.domain.exception.MissingParamsException
import io.github.backjeff.chucknorrisjokes.domain.model.Joke
import io.github.backjeff.chucknorrisjokes.domain.repository.JokeRepository
import kotlinx.coroutines.CoroutineScope

class SearchJoke(
    scope: CoroutineScope,
    private val jokeRepository: JokeRepository
) : UseCase<List<Joke>, SearchJoke.Params>(scope) {

    override fun run(params: Params?) = when {
        params == null -> throw MissingParamsException()
        params.query.isEmpty() -> throw EmptyFieldException()
        else -> jokeRepository.search(params.query)
    }

    data class Params(
        val query: String
    )

}