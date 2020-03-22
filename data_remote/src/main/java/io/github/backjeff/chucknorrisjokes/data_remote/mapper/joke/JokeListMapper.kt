package io.github.backjeff.chucknorrisjokes.data_remote.mapper.joke

import io.github.backjeff.chucknorrisjokes.data_remote.mapper.DataRemoteMapper
import io.github.backjeff.chucknorrisjokes.data_remote.model.joke.RemoteJoke
import io.github.backjeff.chucknorrisjokes.domain.model.Joke

object JokeListMapper: DataRemoteMapper<List<RemoteJoke>, List<Joke>>() {

    override fun toDomain(data: List<RemoteJoke>) =
        data.map {
            Joke(
                id = it.id ?: "",
                url = it.url ?: "",
                value = it.value ?: ""
            )
        }

    override fun fromDomain(data: List<Joke>) =
        data.map {
            RemoteJoke(
                category = null,
                iconUrl = null,
                id = it.id,
                url = it.url,
                value = it.value
            )
        }

}
