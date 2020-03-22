package io.github.backjeff.chucknorrisjokes.feature_random_joke.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import io.github.backjeff.chucknorrisjokes.base.core.BaseFragment
import io.github.backjeff.chucknorrisjokes.base.extensions.getDrawable
import io.github.backjeff.chucknorrisjokes.base.extensions.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

import io.github.backjeff.chucknorrisjokes.feature_random_joke.R
import kotlinx.android.synthetic.main.splash_fragment.*

class SplashFragment : BaseFragment() {

    private val viewModel: SplashViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.splash_fragment, container, false)
    }

    override fun setupView() {
        splashScreenLogo.setOnClickListener {
            viewModel.startCounter()
        }
    }

    override fun addObservers(owner: LifecycleOwner) {
        viewModel.blinkViewState.onPostValue(
            lifecycleOwner = owner,
            onSuccess = { isBlinking ->
                if(isBlinking) {
                    splashScreenLogo.setImageDrawable(getDrawable(R.drawable.logo_blink))
                } else {
                    splashScreenLogo.setImageDrawable(getDrawable(R.drawable.logo))
                }
            }
        )
        viewModel.splashViewState.onPostValue(
            lifecycleOwner = owner,
            onSuccess = {
                toast("Go")
            }
        )
    }

}
