package io.github.backjeff.chucknorrisjokes.feature_random_joke.random_joke

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import io.github.backjeff.chucknorrisjokes.base.core.BaseFragment
import io.github.backjeff.chucknorrisjokes.base.extensions.*
import io.github.backjeff.chucknorrisjokes.feature_random_joke.R
import kotlinx.android.synthetic.main.random_joke_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RandomJokeFragment: BaseFragment() {

    private val viewModel: RandomJokeViewModel by viewModel()
    private val navigation: RandomJokeNavigation by navDirections()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.random_joke_fragment, container, false)
    }

    override fun setupView() {
        setNavigationIcon(null)

        viewModel.getJokeCategoryText()?.run {
            viewModel.requestRandomJoke()
            updateCategoryText(true)
        }

        randomJokeRandomButton.setOnClickListener {
            viewModel.requestRandomJoke()
        }

        randomJokeCategoryButton.setOnClickListener {
            handleCategoryButtonClick()
        }

        randomJokeLogo.setOnClickListener {
            randomJokeLogo.blink()
        }
    }

    private fun handleCategoryButtonClick() {
        viewModel.getJokeCategoryText().let {
            if(it.isNullOrEmpty()) {
                navigation.navigateToJokeCategory()
            } else {
                viewModel.cleanCategory()
                updateCategoryText(false)
            }
        }

    }

    override fun addObservers(owner: LifecycleOwner) {
        viewModel.blinkViewState.onPostValue(
            lifecycleOwner = owner,
            onSuccess = {
                randomJokeLogo.blink()
            }
        )
        viewModel.randomJokeViewState.onPostValue(
            lifecycleOwner = owner,
            onSuccess = {
                randomJokeText.text = it.value
            },
            onError = {
                Log.i("chuck", "${it.message}")
                toast(it.message ?: "")
            }
        )
    }

    private fun updateCategoryText(closeState: Boolean) {
        val categoryText = viewModel.getJokeCategoryText() ?: "Choose a category"
        randomJokeCategoryButton.text = categoryText.capitalize()
        randomJokeCategoryButton.closeState = closeState
    }

    override fun onStop() {
        super.onStop()
        viewModel.clearStates()
    }

}
