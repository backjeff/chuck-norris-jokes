package io.github.backjeff.chucknorrisjokes.domain.interactor.settings.sound

import com.nhaarman.mockitokotlin2.whenever
import io.github.backjeff.chucknorrisjokes.domain.interactor.joke.GetJokeCategories
import io.github.backjeff.chucknorrisjokes.domain.interactor.testFlow
import io.github.backjeff.chucknorrisjokes.domain.interactor.testModule
import io.github.backjeff.chucknorrisjokes.domain.model.JokeCategory
import io.github.backjeff.chucknorrisjokes.domain.repository.JokeRepository
import io.github.backjeff.chucknorrisjokes.domain.repository.SettingsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import org.junit.After
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetSoundConfigTest {

    @Mock
    private lateinit var repository: SettingsRepository

    private lateinit var subject: GetSoundConfig

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        startKoin { modules(testModule) }
        subject = GetSoundConfig(
            scope = CoroutineScope(Dispatchers.Unconfined),
            settingsRepository = repository
        )
    }

    @After
    fun after() {
        stopKoin()
    }

    private fun stubOnSuccess(value: Boolean) {
        whenever(repository.getSoundConfig())
            .thenReturn(flowOf(value))
    }

    private fun stubOnError() {
        whenever(repository.getSoundConfig())
            .thenThrow(Exception())
    }

    @Test
    fun `WHEN succeed MUST not throw an exception and return true`() {
        stubOnSuccess(true)
        subject.run()
            .testFlow {
                assertEquals(true, this)
            }
    }

    @Test
    fun `WHEN succeed MUST not throw an exception and return false`() {
        stubOnSuccess(false)
        subject.run()
            .testFlow {
                assertEquals(false, this)
            }
    }

    @Test(expected = Exception::class)
    fun `WHEN fails MUST throw an exception`() {
        stubOnError()
        subject.run()
    }

}