package io.github.backjeff.chucknorrisjokes.feature_random_joke

import android.app.Application
import io.github.backjeff.chucknorrisjokes.base.core.BaseViewModel
import org.koin.core.KoinComponent

open class BaseRandomJokeViewModel(app: Application) : BaseViewModel(app), KoinComponent {

    companion object {
        var selectedCategory: String? = null
    }

    fun cleanCategory() {
        selectedCategory = null
    }

}
