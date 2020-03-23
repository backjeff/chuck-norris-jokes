package io.github.backjeff.chucknorrisjokes.data_remote.mapper.joke

import io.github.backjeff.chucknorrisjokes.data_remote.mapper.DataRemoteMapper
import io.github.backjeff.chucknorrisjokes.domain.model.JokeCategory

object JokeCategoryMapper: DataRemoteMapper<List<String>, List<JokeCategory>>() {

    override fun toDomain(data: List<String>) =
        data.map {
            JokeCategory(
                value = it
            )
        }

    override fun fromDomain(data: List<JokeCategory>) =
        data.map {
            it.value
        }

}
