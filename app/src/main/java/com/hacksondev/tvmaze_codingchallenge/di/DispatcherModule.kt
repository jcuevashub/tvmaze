package com.hacksondev.tvmaze_codingchallenge.di

import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dispatcherModule = module {

    single(named("io")) { Dispatchers.IO }
}

