package io.github.backjeff.chucknorrisjokes.di

import io.github.backjeff.chucknorrisjokes.feature_random_joke.splash.SplashViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {

    /*viewModel {
        MainViewModel(androidApplication())
    }*/

    viewModel {
        SplashViewModel(androidApplication())
    }

}
