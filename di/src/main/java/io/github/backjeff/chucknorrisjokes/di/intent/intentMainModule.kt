package io.github.backjeff.chucknorrisjokes.di.intent

import androidx.fragment.app.Fragment
import io.github.backjeff.chucknorrisjokes.base.core.BaseFragmentNavigation
import io.github.backjeff.chucknorrisjokes.feature_random_joke.category.JokeCategoryNavigation
import io.github.backjeff.chucknorrisjokes.feature_random_joke.random_joke.RandomJokeNavigation
import io.github.backjeff.chucknorrisjokes.feature_random_joke.splash.SplashScreenNavigation
import io.github.backjeff.chucknorrisjokes.intent.navigation.BaseFragmentNavigationImpl
import io.github.backjeff.chucknorrisjokes.intent.navigation.random_joke.JokeCategoryNavigationImpl
import io.github.backjeff.chucknorrisjokes.intent.navigation.random_joke.RandomJokeNavigationImpl
import io.github.backjeff.chucknorrisjokes.intent.navigation.random_joke.SplashScreenNavigationImpl
import org.koin.dsl.bind
import org.koin.dsl.module


val intentMainModule = module {

    factory { (fragment: Fragment) ->
        BaseFragmentNavigationImpl(fragment)
    } bind BaseFragmentNavigation::class

    factory { (fragment: Fragment) ->
        RandomJokeNavigationImpl(fragment)
    } bind RandomJokeNavigation::class

    factory { (fragment: Fragment) ->
        SplashScreenNavigationImpl(fragment)
    } bind SplashScreenNavigation::class

    factory { (fragment: Fragment) ->
        JokeCategoryNavigationImpl(fragment)
    } bind JokeCategoryNavigation::class

}
