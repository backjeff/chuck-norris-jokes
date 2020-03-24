package io.github.backjeff.chucknorrisjokes.domain.interactor.joke

import com.nhaarman.mockitokotlin2.whenever
import io.github.backjeff.chucknorrisjokes.domain.interactor.testFlow
import io.github.backjeff.chucknorrisjokes.domain.interactor.testModule
import io.github.backjeff.chucknorrisjokes.domain.model.JokeCategory
import io.github.backjeff.chucknorrisjokes.domain.repository.JokeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetJokeCategoriesTest {

    @Mock
    private lateinit var response: List<JokeCategory>

    @Mock
    private lateinit var repository: JokeRepository

    private lateinit var subject: GetJokeCategories

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        startKoin { modules(testModule) }
        subject = GetJokeCategories(
            scope = CoroutineScope(Dispatchers.Unconfined),
            jokeRepository = repository
        )
    }

    @After
    fun after() {
        stopKoin()
    }

    private fun stubOnSuccess() {
        whenever(repository.getCategories())
            .thenReturn(flowOf(response))
    }

    private fun stubOnError() {
        whenever(repository.getCategories())
            .thenThrow(Exception())
    }

    @Test
    fun `WHEN succeed MUST not throw an exception and return an object`() {
        stubOnSuccess()
        subject.run()
            .testFlow {
                Assert.assertEquals(response, this)
            }
    }

    @Test(expected = Exception::class)
    fun `WHEN fails MUST throw an exception`() {
        stubOnError()
        subject.run()
    }


}