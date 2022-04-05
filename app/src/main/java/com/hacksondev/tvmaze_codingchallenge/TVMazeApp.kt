package com.hacksondev.tvmaze_codingchallenge

import android.app.Application
import com.hacksondev.tvmaze_codingchallenge.database.ShowDatabase
import com.hacksondev.tvmaze_codingchallenge.network.TVMazeService
import com.hacksondev.tvmaze_codingchallenge.repository.ShowRepository
import org.koin.android.BuildConfig
import timber.log.Timber

class TVMazeApp : Application() {
    val database by lazy { ShowDatabase.getDatabase(this) }
    private val api = TVMazeService.getInstance()
    val repository by lazy { ShowRepository(api,database.showDao) }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}