package io.github.backjeff.chucknorrisjokes.data_remote.datasource

import io.github.backjeff.chucknorrisjokes.data.datasource.remote.JokeDataSource
import io.github.backjeff.chucknorrisjokes.data_remote.mapper.joke.JokeCategoryMapper
import io.github.backjeff.chucknorrisjokes.data_remote.mapper.joke.JokeMapper
import io.github.backjeff.chucknorrisjokes.data_remote.service.JokeWebService
import io.github.backjeff.chucknorrisjokes.data_remote.util.RequestWrapper
import kotlinx.coroutines.flow.flow
import org.koin.core.KoinComponent
import org.koin.core.inject

data class JokeDataSourceImpl(
    private val jokeWebService: JokeWebService
): JokeDataSource, KoinComponent {

    private val requestWrapper: RequestWrapper by inject()

    override fun getRandomJoke(categoryId: String?) = flow {
        emit(
            JokeMapper.toDomain(
                requestWrapper.wrapperGenericResponse {
                    jokeWebService.getRandomJoke(categoryId)
                }
            )
        )
    }

    override fun getCategories() = flow {
        emit(
            JokeCategoryMapper.toDomain(
                requestWrapper.wrapperGenericResponse {
                    jokeWebService.getCategories()
                }
            )
        )
    }

    override fun search(query: String) = flow {
        emit(
            requestWrapper.wrapperGenericResponse {
                jokeWebService.search(query)
            }.result.map{
                JokeMapper.toDomain(it)
            }
        )
    }

}
