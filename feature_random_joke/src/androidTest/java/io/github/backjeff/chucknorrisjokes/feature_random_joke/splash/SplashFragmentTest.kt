package io.github.backjeff.chucknorrisjokes.feature_random_joke.splash

import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import io.github.backjeff.chucknorrisjokes.feature_random_joke.R
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class SplashFragmentTest {

    /*private val navigation = mockk<SplashScreenNavigation>()

    private val testModule = module {
        single { navigation }
    }

    @Before
    fun before() {
        startKoin {
            modules(
                testModule
            )
        }
    }

    @After
    fun after() {
        stopKoin()
    }*/

    @Test
    fun logo_MUST_be_shown_WHEN_fragment_launches() {

        launchFragmentInContainer<SplashFragment>(
            fragmentArgs = null,
            factory = FragmentFactory()
        )

        onView(withId(R.id.splashScreenLogo))
            .check(matches(isDisplayed()))

        onView(withId(R.id.defaultBackground))
            .check(matches(isDisplayed()))

    }

}
