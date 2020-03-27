package io.github.backjeff.chucknorrisjokes.feature_random_joke.activity

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import androidx.test.rule.ActivityTestRule
import io.github.backjeff.chucknorrisjokes.AppActivity
import io.github.backjeff.chucknorrisjokes.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@MediumTest
@RunWith(AndroidJUnit4::class)
class RandomJokeFragmentTest {

    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(AppActivity::class.java)

    @Test
    fun ts_test() = runBlocking {
        delay(2500)
        onView(withId(R.id.randomJokeText)).check(matches(isDisplayed()))
        onView(withId(R.id.randomJokeLogo)).check(matches(isDisplayed()))
        onView(withId(R.id.randomJokeRandomButton)).check(matches(isDisplayed()))

        onView(withId(R.id.randomJokeCategoryButton)).check(matches(isDisplayed()))
        onView(withId(R.id.randomJokeCategoryButton)).check(matches(withText("Choose a category")))

        Unit // runBlocking return
    }


}
