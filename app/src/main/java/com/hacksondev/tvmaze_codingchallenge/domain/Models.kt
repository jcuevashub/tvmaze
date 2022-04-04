package com.hacksondev.tvmaze_codingchallenge.domain

import android.os.Parcelable
import com.hacksondev.tvmaze_codingchallenge.database.DatabaseImage
import com.hacksondev.tvmaze_codingchallenge.database.DatabaseShow
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Show(
    val id: Long,
    val name: String,
    val image: Image,
    val summary: String
) : Parcelable

@Parcelize
data class Image(
    val medium: String,
    val original: String
    ): Parcelable

fun List<Show>.asDatabaseModel(): Array<DatabaseShow> {
    return map {
        DatabaseShow(
            id = it.id,
            name = it.name,
            image = DatabaseImage(
                medium = it.image.medium,
                original = it.image.original
            ),
            summary = it.summary
        )
    }.toTypedArray()
}