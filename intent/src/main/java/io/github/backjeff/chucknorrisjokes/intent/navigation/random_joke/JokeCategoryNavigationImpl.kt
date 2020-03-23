package io.github.backjeff.chucknorrisjokes.intent.navigation.random_joke

import androidx.fragment.app.Fragment
import io.github.backjeff.chucknorrisjokes.feature_random_joke.category.JokeCategoryNavigation
import io.github.backjeff.chucknorrisjokes.intent.R
import io.github.backjeff.chucknorrisjokes.intent.utils.popBackStack

class JokeCategoryNavigationImpl(
    val fragment: Fragment
): JokeCategoryNavigation {

    override fun navigateToRandomJoke() {
        fragment.popBackStack(R.id.randomJokeFragment)
    }

}
