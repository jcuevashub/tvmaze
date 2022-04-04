package com.hacksondev.tvmaze_codingchallenge.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hacksondev.tvmaze_codingchallenge.base.BaseListRepository
import com.hacksondev.tvmaze_codingchallenge.base.BaseViewModel
import com.hacksondev.tvmaze_codingchallenge.domain.Episode
import com.hacksondev.tvmaze_codingchallenge.domain.Show
import com.hacksondev.tvmaze_codingchallenge.repository.ShowRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val repository: ShowRepository) : ViewModel() {
    val showsList = MutableLiveData<List<Show>>()
    val episodes = MutableLiveData<List<Episode>>()

    val errorMessage = MutableLiveData<String>()

    fun getAllShows() {
        val response = repository.getAllShows()
        response.enqueue(object : Callback<List<Show>> {
            override fun onResponse(call: Call<List<Show>>, response: Response<List<Show>>) {
                response.body()?.let { repository.saveFetchResult(it) }
                showsList.postValue(response.body())
            }
            override fun onFailure(call: Call<List<Show>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }

    fun getAllEpisodeByShows(showId: String) {
        val response = repository.getAllEpisodeByShows(showId)
        response.enqueue(object : Callback<List<Episode>> {
            override fun onResponse(call: Call<List<Episode>>, response: Response<List<Episode>>) {

                episodes.postValue(response.body())
            }
            override fun onFailure(call: Call<List<Episode>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}