package com.hacksondev.tvmaze_codingchallenge.network
import com.hacksondev.tvmaze_codingchallenge.domain.Episode
import com.hacksondev.tvmaze_codingchallenge.domain.Show
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface TVMazeService {

    @GET("shows")
     fun fetchShowList(): Call<List<Show>>

    @GET("shows/{id}/episodes")
    fun fetchShowEpisodesById(@Path("id") id: String): Call<List<Episode>>

    companion object {
        var retrofitService: TVMazeService? = null

        fun getInstance() : TVMazeService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.tvmaze.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(TVMazeService::class.java)
            }
            return retrofitService!!
        }
    }
}