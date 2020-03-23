package io.github.backjeff.chucknorrisjokes.feature_random_joke.category

import android.app.Application
import io.github.backjeff.chucknorrisjokes.base.extensions.*
import io.github.backjeff.chucknorrisjokes.domain.interactor.joke.GetJokeCategories
import io.github.backjeff.chucknorrisjokes.domain.model.JokeCategory
import io.github.backjeff.chucknorrisjokes.feature_random_joke.BaseRandomJokeViewModel
import org.koin.core.KoinComponent

class JokeCategoryViewModel(app: Application): BaseRandomJokeViewModel(app), KoinComponent {

    private val _jokeCategorySelectedViewState by viewState<Unit>()
    val jokeCategorySelectedViewState = _jokeCategorySelectedViewState.asLiveData()


    private val _jokeCategoriesViewState by viewState<List<JokeCategory>>()
    val jokeCategoriesViewState = _jokeCategoriesViewState.asLiveData()

    private val getJokeCategories: GetJokeCategories by useCase()

    init {
        requestJokeCategories()
    }

    fun selectCategory(category: String) {
        selectedCategory = category
        _jokeCategorySelectedViewState.postSuccess(Unit)
    }

    private fun requestJokeCategories() {
        _jokeCategoriesViewState.postLoading()
        getJokeCategories(
            onSuccess = {
                _jokeCategoriesViewState.postSuccess(it)
            },
            onError = {
                _jokeCategoriesViewState.postError(it)
            }
        )
    }

}
