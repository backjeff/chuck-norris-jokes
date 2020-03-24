package io.github.backjeff.chucknorrisjokes.domain.interactor.joke

import com.nhaarman.mockitokotlin2.whenever
import io.github.backjeff.chucknorrisjokes.domain.exception.EmptyFieldException
import io.github.backjeff.chucknorrisjokes.domain.exception.MissingParamsException
import io.github.backjeff.chucknorrisjokes.domain.interactor.testFlow
import io.github.backjeff.chucknorrisjokes.domain.interactor.testModule
import io.github.backjeff.chucknorrisjokes.domain.model.Joke
import io.github.backjeff.chucknorrisjokes.domain.repository.JokeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class SearchJokeTest {

    @Mock
    private lateinit var response: List<Joke>

    @Mock
    private lateinit var repository: JokeRepository

    private lateinit var subject: SearchJoke

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        startKoin { modules(testModule) }
        subject = SearchJoke(
            scope = CoroutineScope(Dispatchers.Unconfined),
            jokeRepository = repository
        )
    }

    @After
    fun after() {
        stopKoin()
    }

    private fun stubOnSuccess() {
        whenever(repository.search(anyString()))
            .thenReturn(flowOf(response))
    }

    private fun stubOnError() {
        whenever(repository.search(anyString()))
            .thenThrow(Exception())
    }

    @Test
    fun `WHEN succeed MUST not throw an exception and return an object`() {
        val params = SearchJoke.Params(
            query = "dev"
        )

        stubOnSuccess()
        subject.run(params)
            .testFlow {
                assertEquals(response, this)
            }
    }

    @Test(expected = Exception::class)
    fun `WHEN fails MUST throw an exception`() {
        stubOnError()
        subject.run()
    }

    @Test(expected = MissingParamsException::class)
    fun `WHEN dont receive params MUST throw an exception`() {
        subject.run()
    }

    @Test(expected = EmptyFieldException::class)
    fun `WHEN receive empty param MUST throw an exception`() {
        val params = SearchJoke.Params(
            query = ""
        )

        stubOnSuccess()
        subject.run(params)
            .testFlow {
                assertEquals(response, this)
            }
    }

}