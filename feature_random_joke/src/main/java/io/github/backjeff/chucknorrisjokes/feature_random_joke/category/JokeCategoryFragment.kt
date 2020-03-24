package io.github.backjeff.chucknorrisjokes.feature_random_joke.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import io.github.backjeff.chucknorrisjokes.base.core.BaseFragment
import io.github.backjeff.chucknorrisjokes.base.extensions.navDirections
import io.github.backjeff.chucknorrisjokes.base.extensions.toast
import io.github.backjeff.chucknorrisjokes.feature_random_joke.R
import kotlinx.android.synthetic.main.joke_category_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class JokeCategoryFragment: BaseFragment() {

    private val viewModel: JokeCategoryViewModel by viewModel()
    private val navigation: JokeCategoryNavigation by navDirections()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.joke_category_fragment, container, false)
    }

    override fun addObservers(owner: LifecycleOwner) {
        viewModel.jokeCategorySelectedViewState.onPostValue(
            lifecycleOwner = owner,
            onSuccess = {
                navigation.navigateToRandomJoke()
            }
        )
        viewModel.jokeCategoriesViewState.onPostValue(
            lifecycleOwner = owner,
            onSuccess = { list ->
                jokeCategoryList.addItems(list.map { it.value }) {
                    viewModel.selectCategory(list[it].value)
                }
            },
            onError = {
                toast(it.message ?: "")
            }
        )
    }

}
