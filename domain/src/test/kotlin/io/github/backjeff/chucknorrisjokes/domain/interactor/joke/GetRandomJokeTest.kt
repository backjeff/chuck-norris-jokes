package io.github.backjeff.chucknorrisjokes.domain.interactor.joke

import com.nhaarman.mockitokotlin2.whenever
import io.github.backjeff.chucknorrisjokes.domain.interactor.testFlow
import io.github.backjeff.chucknorrisjokes.domain.interactor.testModule
import io.github.backjeff.chucknorrisjokes.domain.model.Joke
import io.github.backjeff.chucknorrisjokes.domain.repository.JokeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetRandomJokeTest {

    @Mock
    private lateinit var response: Joke

    @Mock
    private lateinit var repository: JokeRepository

    private lateinit var subject: GetRandomJoke

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        startKoin { modules(testModule) }
        subject = GetRandomJoke(
            scope = CoroutineScope(Dispatchers.Unconfined),
            jokeRepository = repository
        )
    }

    @After
    fun after() {
        stopKoin()
    }

    private fun stubOnSuccess() {
        whenever(repository.getRandomJoke(anyString()))
            .thenReturn(flowOf(response))
    }

    private fun stubOnError() {
        whenever(repository.getRandomJoke(anyString()))
            .thenThrow(Exception())
    }

    @Test
    fun `WHEN succeed MUST not throw an exception and return an object`() {
        stubOnSuccess()
        subject.run(
            GetRandomJoke.Params(
                categoryId = "dev"
            )
        ).testFlow {
            assertEquals(response, this)
        }
    }

    @Test(expected = Exception::class)
    fun `WHEN fails MUST throw an exception`() {
        stubOnError()
        subject.run()
    }

    @Test
    fun `WHEN dont receive params MUST not throw an exception and return an object`() {
        stubOnSuccess()
        subject.run(GetRandomJoke.Params())
            .testFlow {
                assertEquals(response, this)
            }
    }

    @Test
    fun `WHEN receive empty param MUST not throw an exception and return an object`() {
        stubOnSuccess()
        subject.run(
            GetRandomJoke.Params(
                categoryId = ""
            )
        ).testFlow {
            assertEquals(response, this)
        }
    }

}