package io.github.backjeff.chucknorrisjokes.feature_random_joke.fragment

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation.setViewNavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.github.backjeff.chucknorrisjokes.R
import io.github.backjeff.chucknorrisjokes.feature_random_joke.random_joke.RandomJokeFragment
import io.github.backjeff.chucknorrisjokes.feature_random_joke.random_joke.RandomJokeViewModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations.initMocks

@RunWith(AndroidJUnit4::class)
class RandomJokeFragmentTest {

    private lateinit var scenario: FragmentScenario<RandomJokeFragment>

    @Mock
    lateinit var navController: NavController

    @Mock
    lateinit var randomJokeViewModel: RandomJokeViewModel

    @Before
    fun startMockito() = initMocks(this)

    @Before
    fun inject() {
        loadKoinModules( module {
            viewModel { randomJokeViewModel }
        })
    }

    @Before
    fun startFragment() {
        scenario = launchFragmentInContainer<RandomJokeFragment>(
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
        onView(withId(R.id.randomJokeLogo)).check(matches(isDisplayed()))
        onView(withId(R.id.randomJokeCategoryButton)).check(matches(withText("Choose a category")))
    }

    @Test
    fun test_navigations() {
        // GIVEN

        // WHEN
        onView(withId(R.id.randomJokeCategoryButton)).perform(click())

        // THEN
        verify(navController).navigate(R.id.jokeCategoryFragment, null, null)

    }

    @Test
    fun test_usecase_calls() {
        // GIVEN

        // WHEN
        onView(withId(R.id.randomJokeRandomButton)).perform(click())

        // THEN
        verify(randomJokeViewModel, times(1)).requestRandomJoke()

    }

}
