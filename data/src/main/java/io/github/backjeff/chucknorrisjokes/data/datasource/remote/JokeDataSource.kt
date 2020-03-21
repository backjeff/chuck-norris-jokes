package io.github.backjeff.chucknorrisjokes.data.datasource.remote

import io.github.backjeff.chucknorrisjokes.domain.model.Joke
import io.github.backjeff.chucknorrisjokes.domain.model.JokeCategory
import kotlinx.coroutines.flow.Flow

interface JokeDataSource {
    fun getRandomJoke(categoryId: Int?): Flow<Joke>
    fun getCategories(): Flow<List<JokeCategory>>
    fun search(query: String?): Flow<List<Joke>>
}