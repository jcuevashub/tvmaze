package com.hacksondev.tvmaze_codingchallenge.di

import org.koin.dsl.module

val repositoryModule = module {

    //single<BaseListRepository<Show>> { ShowRepository(get(), get(), get(), get(named("io"))) }
}