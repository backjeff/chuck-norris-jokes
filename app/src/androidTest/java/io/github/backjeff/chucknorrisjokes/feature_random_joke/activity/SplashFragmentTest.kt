package io.github.backjeff.chucknorrisjokes.feature_random_joke.activity

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import io.github.backjeff.chucknorrisjokes.AppActivity
import io.github.backjeff.chucknorrisjokes.feature_random_joke.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SplashFragmentTest {

    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(AppActivity::class.java)

    @Test
    fun splashScreen_test() = runBlocking {
        onView(withId(R.id.splashScreenLogo)).check(matches(isDisplayed()))
        delay(2500)
        onView(withId(R.id.splashScreenLogo)).check(doesNotExist())

        Unit // runBlocking return
    }


}