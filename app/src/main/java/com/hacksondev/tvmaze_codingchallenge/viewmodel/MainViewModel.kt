package com.hacksondev.tvmaze_codingchallenge.viewmodel

import com.hacksondev.tvmaze_codingchallenge.base.BaseListRepository
import com.hacksondev.tvmaze_codingchallenge.base.BaseViewModel
import com.hacksondev.tvmaze_codingchallenge.domain.Show

class MainViewModel(repository: BaseListRepository<Show>) : BaseViewModel<List<Show>>(repository)