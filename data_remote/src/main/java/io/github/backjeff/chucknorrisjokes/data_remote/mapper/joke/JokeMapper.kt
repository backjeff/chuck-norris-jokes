package io.github.backjeff.chucknorrisjokes.data_remote.mapper.joke

import io.github.backjeff.chucknorrisjokes.data_remote.mapper.DataRemoteMapper
import io.github.backjeff.chucknorrisjokes.data_remote.model.joke.RemoteJoke
import io.github.backjeff.chucknorrisjokes.domain.model.Joke

object JokeMapper: DataRemoteMapper<RemoteJoke, Joke>() {

    override fun toDomain(data: RemoteJoke) =
        Joke(
            id = data.id ?: "",
            url = data.url ?: "",
            value = data.value ?: ""
        )

    override fun fromDomain(data: Joke) =
        RemoteJoke(
            category = null,
            iconUrl = null,
            id = data.id,
            url = data.url,
            value = data.value
        )

}