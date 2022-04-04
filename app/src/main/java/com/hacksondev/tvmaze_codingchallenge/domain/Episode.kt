package com.hacksondev.tvmaze_codingchallenge.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Episode(
    val id: Long,
    val name: String,
    val summary: String
) : Parcelable


//fun List<Episode>.asDatabaseModel(): Array<DatabaseEpisode> {
//    return map {
//        DatabaseEpisode(
//            id = it.id,
//            name = it.name,
//            summary = it.summary
//            )
//    }.toTypedArray()
//}