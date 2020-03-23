package io.github.backjeff.chucknorrisjokes.feature_random_joke.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import io.github.backjeff.chucknorrisjokes.base.core.BaseFragment
import io.github.backjeff.chucknorrisjokes.base.extensions.getDrawable
import io.github.backjeff.chucknorrisjokes.base.extensions.navDirections
import io.github.backjeff.chucknorrisjokes.base.extensions.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

import io.github.backjeff.chucknorrisjokes.feature_random_joke.R
import kotlinx.android.synthetic.main.splash_fragment.*

class SplashFragment : BaseFragment() {

    private val viewModel: SplashViewModel by viewModel()
    private val navigation: SplashScreenNavigation by navDirections()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.splash_fragment, container, false)
    }

    override fun setupView() {
        splashScreenLogo.setOnClickListener {
            splashScreenLogo.blink()
        }
    }

    override fun addObservers(owner: LifecycleOwner) {
        viewModel.blinkViewState.onPostValue(
            lifecycleOwner = owner,
            onSuccess = {
                splashScreenLogo.blink {
                    navigation.navigateToRandomJoke()
                }
            }
        )
    }

}
