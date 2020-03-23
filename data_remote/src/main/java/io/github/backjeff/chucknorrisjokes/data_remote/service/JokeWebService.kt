package io.github.backjeff.chucknorrisjokes.data_remote.service

import io.github.backjeff.chucknorrisjokes.data_remote.model.joke.RemoteJoke
import io.github.backjeff.chucknorrisjokes.data_remote.model.joke.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface JokeWebService {
    @GET("jokes/random")
    suspend fun getRandomJoke(@Query("category") categoryId: String?): RemoteJoke

    @GET("jokes/categories")
    suspend fun getCategories(): List<String>

    @GET("jokes/search")
    suspend fun search(@Query("query") query: String): SearchResponse
}
