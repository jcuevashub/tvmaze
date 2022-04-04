package com.hacksondev.tvmaze_codingchallenge.base

import android.content.Context
import com.hacksondev.tvmaze_codingchallenge.R
import com.hacksondev.tvmaze_codingchallenge.util.Resource
import com.hacksondev.tvmaze_codingchallenge.util.isNetworkAvailable
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseRepository<T>(
    context: Context,
    dispatcher: CoroutineDispatcher
) {

    protected abstract suspend fun query(): T

    protected abstract suspend fun fetch(): T

    protected abstract suspend fun saveFetchResult(items: T)

    protected open fun isNotEmpty(t: T) = t != null

    val result: Flow<Resource<T>> = flow {
        emit(Resource.loading())
        query().let {

            if (isNotEmpty(it)) {
                // ****** STEP 1: VIEW CACHE ******
                emit(Resource.success(it))
            }

            if (context.isNetworkAvailable()) {
                try {
                    // ****** STEP 2: MAKE NETWORK CALL, SAVE RESULT TO CACHE ******
                    refresh()
                    // ****** STEP 3: VIEW CACHE ******
                    emit(Resource.success(query()))
                } catch (t: Throwable) {
                    if (isNotEmpty(it)) {
                        return@flow
                    }
                    emit(Resource.error(context.getString(R.string.failed_loading_msg),null))
                }

            } else {
                if (isNotEmpty(it)) {
                    return@flow
                }
                emit(Resource.error(context.getString(R.string.failed_network_msg),null))
            }
        }
    }.flowOn(dispatcher)

    suspend fun refresh() {
        saveFetchResult(fetch())
    }
}