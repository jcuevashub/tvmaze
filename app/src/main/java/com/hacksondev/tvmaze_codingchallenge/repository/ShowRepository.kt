package com.hacksondev.tvmaze_codingchallenge.repository

import com.hacksondev.tvmaze_codingchallenge.domain.Show
import com.hacksondev.tvmaze_codingchallenge.network.TVMazeService

class ShowRepository(
        private val api: TVMazeService,
)  {
      fun getAllShows() = api.fetchShowList()

      fun getAllEpisodeByShows(showId: String) = api.fetchShowEpisodesById(showId)

      fun saveFetchResult(items: List<Show>) {
        //dao.insertAll(*items.asDatabaseModel())
    }

}