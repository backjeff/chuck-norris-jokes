package io.github.backjeff.chucknorrisjokes.feature_random_joke.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.backjeff.chucknorrisjokes.base.core.BaseFragment
import io.github.backjeff.chucknorrisjokes.base.extensions.navDirections
import io.github.backjeff.chucknorrisjokes.feature_random_joke.R
import kotlinx.android.synthetic.main.splash_fragment.*

class SplashFragment : BaseFragment() {

    private val navigation: SplashScreenNavigation by navDirections()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.splash_fragment, container, false)
    }

    override fun setupView() {
        splashScreenLogo.blink {
            navigation.navigateToRandomJoke()
        }
    }

}
