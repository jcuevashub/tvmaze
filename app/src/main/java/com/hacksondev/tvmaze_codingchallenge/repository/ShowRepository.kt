package com.hacksondev.tvmaze_codingchallenge.repository

import android.content.Context
import com.hacksondev.tvmaze_codingchallenge.base.BaseListRepository
import com.hacksondev.tvmaze_codingchallenge.database.ShowDao
import com.hacksondev.tvmaze_codingchallenge.database.asDomainModel
import com.hacksondev.tvmaze_codingchallenge.domain.Show
import com.hacksondev.tvmaze_codingchallenge.domain.asDatabaseModel
import com.hacksondev.tvmaze_codingchallenge.services.TVMazeService
import kotlinx.coroutines.CoroutineDispatcher

class ShowRepository(
    private val dao: ShowDao,
    private val api: TVMazeService,
    context: Context,
    dispatcher: CoroutineDispatcher
) : BaseListRepository<Show>(context, dispatcher) {

    override suspend fun query(): List<Show> = dao.getShows().asDomainModel()

    override suspend fun fetch(): List<Show> = api.fetchShowList()

    override suspend fun saveFetchResult(items: List<Show>) {
        dao.insertAll(*items.asDatabaseModel())
    }

}