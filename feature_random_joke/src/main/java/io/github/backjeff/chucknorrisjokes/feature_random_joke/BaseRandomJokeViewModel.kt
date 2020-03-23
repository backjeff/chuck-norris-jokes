package io.github.backjeff.chucknorrisjokes.feature_random_joke

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import org.koin.core.KoinComponent

open class BaseRandomJokeViewModel(app: Application) : AndroidViewModel(app), KoinComponent {

    companion object {
        var selectedCategory: String? = null
    }

    fun cleanCategory() {
        selectedCategory = null
    }

}
