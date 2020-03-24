package io.github.backjeff.chucknorrisjokes.feature_random_joke.random_joke

import android.os.Bundle
import android.view.*
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
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.random_joke_fragment, container, false)
    }

    override fun setupView() {
        setNavigationIcon(null)

        viewModel.getSoundConfig()

        viewModel.getJokeCategoryText()?.run {
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
                randomJokeLogo.blink(it)
            }
        )
        viewModel.randomJokeViewState.onPostValue(
            lifecycleOwner = owner,
            onSuccess = {
                randomJokeText.text = it.value
            },
            onError = {
                toast(it.message ?: "")
            }
        )
        viewModel.soundConfigState.onPostValue(
            lifecycleOwner = owner,
            onSuccess = {
                viewModel.requestRandomJoke()
                handleSoundState(it)
            }
        )
        viewModel.toggleSoundConfigState.onPostValue(
            lifecycleOwner = owner,
            onSuccess = { enabled ->
                handleSoundState(enabled)
            }
        )
    }

    private fun handleSoundState(enabled: Boolean) {
        if (enabled) {
            setSoundIcon(R.drawable.ic_sound_on)
        } else {
            setSoundIcon(R.drawable.ic_sound_off)
        }
        randomJokeLogo.setOnClickListener { randomJokeLogo.blink(enabled) }
    }

    private fun setSoundIcon(drawableId: Int) {
        getMenuItem(0)?.icon = getDrawable(drawableId)
    }

    private fun updateCategoryText(closeState: Boolean) {
        val categoryText = viewModel.getJokeCategoryText() ?: "Choose a category"
        randomJokeCategoryButton.text = categoryText.capitalize()
        randomJokeCategoryButton.closeState = closeState
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.random_joke_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_sound -> {
            viewModel.toggleSound()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onStop() {
        super.onStop()
        viewModel.clearStates()
    }

}
