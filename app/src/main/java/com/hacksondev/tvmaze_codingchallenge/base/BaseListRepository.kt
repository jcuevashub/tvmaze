package com.hacksondev.tvmaze_codingchallenge.base

import android.content.Context
import kotlinx.coroutines.CoroutineDispatcher

abstract class BaseListRepository<T>(
        context: Context,
        dispatcher: CoroutineDispatcher
) : BaseRepository<List<T>>(context, dispatcher) {

    override fun isNotEmpty(t: List<T>): Boolean = t.isNotEmpty()
}