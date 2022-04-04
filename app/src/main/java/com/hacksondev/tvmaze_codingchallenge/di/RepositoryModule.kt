package com.hacksondev.tvmaze_codingchallenge.di

import com.hacksondev.tvmaze_codingchallenge.base.BaseListRepository
import com.hacksondev.tvmaze_codingchallenge.domain.Show
import com.hacksondev.tvmaze_codingchallenge.repository.ShowRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {

    single<BaseListRepository<Show>> { ShowRepository(get(), get(), get(), get(named("io"))) }
}