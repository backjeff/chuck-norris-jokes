package io.github.backjeff.chucknorrisjokes.di.intent

import androidx.fragment.app.Fragment
import io.github.backjeff.chucknorrisjokes.base.core.BaseFragmentNavigation
import io.github.backjeff.chucknorrisjokes.intent.navigation.BaseFragmentNavigationImpl
import org.koin.dsl.bind
import org.koin.dsl.module


val intentMainModule = module {

    /*factory { (fragment: Fragment) ->
        MainNavigationImpl(fragment)
    } bind MainNavigation::class*/

    factory { (fragment: Fragment) ->
        BaseFragmentNavigationImpl(fragment)
    } bind BaseFragmentNavigation::class

}
