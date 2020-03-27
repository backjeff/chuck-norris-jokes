package io.github.backjeff.chucknorrisjokes.feature_random_joke.fragment

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation.setViewNavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.github.backjeff.chucknorrisjokes.R
import io.github.backjeff.chucknorrisjokes.feature_random_joke.splash.SplashFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations.initMocks

@RunWith(AndroidJUnit4::class)
class SplashFragmentTest {

    private lateinit var scenario: FragmentScenario<SplashFragment>

    @Mock
    lateinit var navController: NavController

    @Before
    fun startMockito() = initMocks(this)

    @Before
    fun startFragment() {
        scenario = launchFragmentInContainer<SplashFragment>(
            fragmentArgs = null,
            themeResId = R.style.AppTheme
        ).also { fragmentScenario ->
            fragmentScenario.onFragment {
                setViewNavController(it.view!!, navController)
            }
        }
    }

    @Test
    fun test_views() {
        // GIVEN

        // WHEN

        // THEN
        onView(withId(R.id.splashScreenLogo)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.defaultBackground)).check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun test_navigations() = runBlocking {
        // GIVEN

        // WHEN
        delay(4000)

        // THEN
        verify(navController).navigate(R.id.randomJokeFragment, null, null)

        Unit
    }

}
