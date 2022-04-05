package com.hacksondev.tvmaze_codingchallenge.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hacksondev.tvmaze_codingchallenge.database.DatabaseImage
import com.hacksondev.tvmaze_codingchallenge.database.DatabaseShow
import com.hacksondev.tvmaze_codingchallenge.domain.Cast
import com.hacksondev.tvmaze_codingchallenge.domain.Episode
import com.hacksondev.tvmaze_codingchallenge.domain.Image
import com.hacksondev.tvmaze_codingchallenge.domain.Show
import com.hacksondev.tvmaze_codingchallenge.repository.ShowRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val repository: ShowRepository) : ViewModel() {
    val showsList = MutableLiveData<List<Show>>()
    val castList = MutableLiveData<List<Cast>>()

    val episodes = MutableLiveData<List<Episode>>()
    val allShows = MutableLiveData<List<Show>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllShows() {
        val response = repository.getAllShows()
        response.enqueue(object : Callback<List<Show>> {
            override fun onResponse(call: Call<List<Show>>, response: Response<List<Show>>) {
                showsList.postValue(response.body())
            }
            override fun onFailure(call: Call<List<Show>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }

    fun getShowCast(showId: String) {
        val response = repository.fetchShowCast(showId)
        response.enqueue(object : Callback<List<Cast>> {
            override fun onResponse(call: Call<List<Cast>>, response: Response<List<Cast>>) {
                castList.postValue(response.body())
            }
            override fun onFailure(call: Call<List<Cast>>, t: Throwable) {
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

    fun getFavorites() {
        val shows = repository.getAllFavoritesShows()
        allShows.postValue(shows)
    }

    fun removeFavorites(show: Show) = viewModelScope.launch  {
        repository.remove(DatabaseShow(
            id = show.id,
            name = show.name,
            image = DatabaseImage(
                medium = show.image.medium,
                original = show.image.medium
            ),
            summary = show.summary,
            type = show.type,
            language = show.language,
            status = show.status,
            runtime = show.runtime,
            premiered = show.premiered,
            weight = show.weight,
            genres = show.genres,
            schedule = show.schedule
        ))
    }

    fun addFavorite(show: Show) = viewModelScope.launch {
        repository.insert(DatabaseShow(
            id = show.id,
            name = show.name,
            image = DatabaseImage(
                medium = show.image.medium,
                original = show.image.medium
            ),
            summary = show.summary,
            type = show.type,
            language = show.language,
            status = show.status,
            runtime = show.runtime,
            premiered = show.premiered,
            weight = show.weight,
            genres = show.genres,
            schedule = show.schedule

        ))
    }
}