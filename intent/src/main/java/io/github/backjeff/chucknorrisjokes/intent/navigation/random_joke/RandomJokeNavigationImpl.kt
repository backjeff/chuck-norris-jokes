package io.github.backjeff.chucknorrisjokes.intent.navigation.random_joke

import androidx.fragment.app.Fragment
import io.github.backjeff.chucknorrisjokes.feature_random_joke.random_joke.RandomJokeNavigation
import io.github.backjeff.chucknorrisjokes.intent.R
import io.github.backjeff.chucknorrisjokes.intent.utils.navigate

class RandomJokeNavigationImpl(
    val fragment: Fragment
): RandomJokeNavigation {

    override fun navigateToJokeCategory() = fragment.navigate(
        R.id.jokeCategoryFragment
    )

}
