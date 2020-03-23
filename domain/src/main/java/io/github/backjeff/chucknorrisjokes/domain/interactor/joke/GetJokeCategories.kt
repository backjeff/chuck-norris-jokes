package io.github.backjeff.chucknorrisjokes.domain.interactor.joke

import io.github.backjeff.chucknorrisjokes.domain.core.UseCase
import io.github.backjeff.chucknorrisjokes.domain.exception.MissingParamsException
import io.github.backjeff.chucknorrisjokes.domain.model.JokeCategory
import io.github.backjeff.chucknorrisjokes.domain.repository.JokeRepository
import kotlinx.coroutines.CoroutineScope

class GetJokeCategories(
    scope: CoroutineScope,
    private val jokeRepository: JokeRepository
) : UseCase<List<JokeCategory>, Unit>(scope) {

    override fun run(params: Unit?) = jokeRepository.getCategories()

}