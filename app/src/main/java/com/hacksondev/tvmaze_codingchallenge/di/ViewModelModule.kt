package com.hacksondev.tvmaze_codingchallenge.di

import com.hacksondev.tvmaze_codingchallenge.viewmodel.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { MainViewModel(get()) }
}