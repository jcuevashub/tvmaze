package com.hacksondev.tvmaze_codingchallenge.repository

import androidx.annotation.WorkerThread
import com.hacksondev.tvmaze_codingchallenge.database.DatabaseShow
import com.hacksondev.tvmaze_codingchallenge.database.ShowDao
import com.hacksondev.tvmaze_codingchallenge.database.asDomainModel
import com.hacksondev.tvmaze_codingchallenge.network.TVMazeService

class ShowRepository(
        private val api: TVMazeService,
        private val dao: ShowDao
)  {
      fun getAllShows() = api.fetchShowList()
            fun getAllFavoritesShows() = dao.getShows().asDomainModel()

      fun getAllEpisodeByShows(showId: String) = api.fetchShowEpisodesById(showId)

      fun fetchShowCast(showId: String) = api.fetchShowCast(showId)

      fun fetchCastInfo(id: String) = api.fetchCastInfo(id)


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(item: DatabaseShow) {
        dao.addData(item)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun remove(item: DatabaseShow) {
        dao.delete(item)
    }
}