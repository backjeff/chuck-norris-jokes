package io.github.backjeff.chucknorrisjokes.domain.interactor.settings.sound

import com.nhaarman.mockitokotlin2.whenever
import io.github.backjeff.chucknorrisjokes.domain.exception.MissingParamsException
import io.github.backjeff.chucknorrisjokes.domain.interactor.testFlow
import io.github.backjeff.chucknorrisjokes.domain.interactor.testModule
import io.github.backjeff.chucknorrisjokes.domain.repository.SettingsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.mockito.ArgumentMatchers.anyBoolean
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class SetSoundConfigTest {

    @Mock
    private lateinit var repository: SettingsRepository

    private lateinit var subject: SetSoundConfig

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        startKoin { modules(testModule) }
        subject = SetSoundConfig(
            scope = CoroutineScope(Dispatchers.Unconfined),
            settingsRepository = repository
        )
    }

    @After
    fun after() {
        stopKoin()
    }

    private fun stubOnSuccess() {
        whenever(repository.setSoundConfig(anyBoolean()))
            .thenReturn(flowOf(Unit))
    }

    private fun stubOnError() {
        whenever(repository.setSoundConfig(anyBoolean()))
            .thenThrow(Exception())
    }

    @Test
    fun `WHEN succeed MUST not throw an exception and return an object`() {
        stubOnSuccess()
        subject.run(
            SetSoundConfig.Params(
                soundConfig = anyBoolean()
            )
        ).testFlow {
            assertEquals(Unit, this)
        }
    }

    @Test(expected = Exception::class)
    fun `WHEN fails MUST throw an exception`() {
        stubOnError()
        subject.run()
    }

    @Test(expected = MissingParamsException::class)
    fun `WHEN dont receive params MUST throw an exception`() {
        stubOnSuccess()
        subject.run()
    }

}
