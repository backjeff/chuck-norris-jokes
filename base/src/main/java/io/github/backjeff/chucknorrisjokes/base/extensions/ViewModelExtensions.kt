package io.github.backjeff.chucknorrisjokes.base.extensions

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.backjeff.chucknorrisjokes.base.core.ViewState
import io.github.backjeff.chucknorrisjokes.domain.core.UseCase
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

fun <T> viewState() = lazy {
    MutableLiveData<ViewState<T>>()
}

inline fun <V, reified U> V.useCase() where U : UseCase<*, *>, V : ViewModel, V : KoinComponent = inject<U> {
    parametersOf(viewModelScope)
}

fun AndroidViewModel.getString(resId: Int): String = getApplication<Application>().getString(resId)
