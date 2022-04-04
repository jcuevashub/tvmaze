package com.hacksondev.tvmaze_codingchallenge.network
import com.hacksondev.tvmaze_codingchallenge.domain.Show
import retrofit2.http.GET
import retrofit2.http.Path

interface TVMazeService {

    @GET("shows")
    suspend fun fetchShowList(): List<Show>

    @GET("shows/{id}")
    fun fetchShowById(@Path("id") id: String): List<Show>
}