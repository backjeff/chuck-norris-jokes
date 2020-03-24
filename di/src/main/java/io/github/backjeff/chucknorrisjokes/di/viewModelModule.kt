package io.github.backjeff.chucknorrisjokes.di

import io.github.backjeff.chucknorrisjokes.base.core.BaseViewModel
import io.github.backjeff.chucknorrisjokes.feature_random_joke.category.JokeCategoryViewModel
import io.github.backjeff.chucknorrisjokes.feature_random_joke.random_joke.RandomJokeViewModel
import io.github.backjeff.chucknorrisjokes.feature_random_joke.splash.SplashViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {

    viewModel {
        BaseViewModel(androidApplication())
    }

    viewModel {
        SplashViewModel(androidApplication())
    }

    viewModel {
        RandomJokeViewModel(androidApplication())
    }

    viewModel {
        JokeCategoryViewModel(androidApplication())
    }

}
