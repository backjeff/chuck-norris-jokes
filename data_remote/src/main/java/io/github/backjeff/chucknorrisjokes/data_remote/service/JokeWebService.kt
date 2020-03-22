package io.github.backjeff.chucknorrisjokes.data_remote.service

import io.github.backjeff.chucknorrisjokes.data_remote.model.joke.RemoteJoke
import io.github.backjeff.chucknorrisjokes.data_remote.model.joke.RemoteJokeCategory
import io.github.backjeff.chucknorrisjokes.data_remote.model.joke.SearchResponse
import retrofit2.http.GET

interface JokeWebService {
    @GET("jokes/random")
    suspend fun getRandomJoke(categoryId: Int?): RemoteJoke

    @GET("jokes/categories")
    suspend fun getCategories(): List<RemoteJokeCategory>

    @GET("jokes/search")
    suspend fun search(query: String): SearchResponse
}
