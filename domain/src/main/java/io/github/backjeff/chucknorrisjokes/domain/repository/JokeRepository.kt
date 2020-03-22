package io.github.backjeff.chucknorrisjokes.domain.repository

import io.github.backjeff.chucknorrisjokes.domain.model.Joke
import io.github.backjeff.chucknorrisjokes.domain.model.JokeCategory
import kotlinx.coroutines.flow.Flow

interface JokeRepository {
    fun getRandomJoke(categoryId: Int?): Flow<Joke>
    fun getCategories(): Flow<List<JokeCategory>>
    fun search(query: String): Flow<List<Joke>>
}