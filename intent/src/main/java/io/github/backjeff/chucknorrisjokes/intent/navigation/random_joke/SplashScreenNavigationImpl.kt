package io.github.backjeff.chucknorrisjokes.intent.navigation.random_joke

import androidx.fragment.app.Fragment
import io.github.backjeff.chucknorrisjokes.feature_random_joke.splash.SplashScreenNavigation
import io.github.backjeff.chucknorrisjokes.intent.R
import io.github.backjeff.chucknorrisjokes.intent.utils.navigate

class SplashScreenNavigationImpl(
    val fragment: Fragment
): SplashScreenNavigation {

    override fun navigateToRandomJoke() = fragment.navigate(
        R.id.randomJokeFragment
    )

}
