package com.hacksondev.tvmaze_codingchallenge.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.hacksondev.tvmaze_codingchallenge.repository.ShowRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

class RefreshShowWork(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params), KoinComponent {

    companion object {
        const val WORK_NAME = "RefreshShowWork"
    }

    /**
     * A coroutine-friendly method to do your work.
     */
    override suspend fun doWork(): Result {
        val repository: ShowRepository by inject()
        return try {
            repository.refresh()
            Result.success()
        } catch (err: Exception) {
            Result.failure()
        }
    }
}