package com.hacksondev.tvmaze_codingchallenge.di

import androidx.room.Room
import com.hacksondev.tvmaze_codingchallenge.R
import com.hacksondev.tvmaze_codingchallenge.database.ShowDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val persistenceModule = module {

    single {
        Room.databaseBuilder(androidApplication(), ShowDatabase::class.java,
                androidApplication().getString(R.string.database))
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
    }

    single { get<ShowDatabase>().showDao }
}