package io.github.backjeff.chucknorrisjokes.feature_random_joke.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import io.github.backjeff.chucknorrisjokes.base.core.BaseFragment
import io.github.backjeff.chucknorrisjokes.base.extensions.navDirections
import io.github.backjeff.chucknorrisjokes.feature_random_joke.R
import kotlinx.android.synthetic.main.splash_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment() {

    private val viewModel: SplashViewModel by viewModel()
    private val navigation: SplashScreenNavigation by navDirections()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.splash_fragment, container, false)
    }

    override fun addObservers(owner: LifecycleOwner) {
        super.addObservers(owner)
        viewModel.soundConfigState.onFirstPostValue(
            lifecycleOwner = owner,
            onSuccess = { startBlink(it) },
            onError = { startBlink(true) }
        )
    }

    private fun startBlink(sound: Boolean) =
        splashScreenLogo.blink(sound) {
            navigation.navigateToRandomJoke()
        }

}
