package io.github.backjeff.chucknorrisjokes.data_remote.mapper.joke

import io.github.backjeff.chucknorrisjokes.data_remote.mapper.DataRemoteMapper
import io.github.backjeff.chucknorrisjokes.data_remote.model.joke.RemoteJokeCategory
import io.github.backjeff.chucknorrisjokes.domain.model.JokeCategory

object JokeCategoryMapper: DataRemoteMapper<List<RemoteJokeCategory>, List<JokeCategory>>() {

    override fun toDomain(data: List<RemoteJokeCategory>) =
        data.map {
            JokeCategory(
                id = it.id ?: 0,
                value = it.value ?: ""
            )
        }

    override fun fromDomain(data: List<JokeCategory>) =
        data.map {
            RemoteJokeCategory(
                id = it.id,
                value = it.value
            )
        }

}
