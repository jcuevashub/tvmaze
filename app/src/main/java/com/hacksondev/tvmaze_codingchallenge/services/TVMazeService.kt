package com.hacksondev.tvmaze_codingchallenge.services

import com.hacksondev.tvmaze_codingchallenge.domain.Show
import retrofit2.http.GET

interface  TVMazeService {
    @GET("shows")
    suspend fun fetchShowList(): List<Show>
}