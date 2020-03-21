package io.github.backjeff.chucknorrisjokes.data.repository

import io.github.backjeff.chucknorrisjokes.data.datasource.remote.JokeDataSource
import io.github.backjeff.chucknorrisjokes.domain.repository.JokeRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single

class JokeRepositoryImpl(
    private val jokeDataSource: JokeDataSource
): JokeRepository {

    override fun getRandomJoke(categoryId: Int?) = flow {
        emit(
            jokeDataSource.getRandomJoke(categoryId).single()
        )
    }

    override fun getCategories() = flow {
        emit(
            jokeDataSource.getCategories().single()
        )
    }

    override fun search(query: String?) = flow {
        emit(
            jokeDataSource.search(query).single()
        )
    }

}