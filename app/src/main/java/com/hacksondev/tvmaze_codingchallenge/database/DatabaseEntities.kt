package com.hacksondev.tvmaze_codingchallenge.database

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hacksondev.tvmaze_codingchallenge.domain.*
import com.hacksondev.tvmaze_codingchallenge.domain.Show

@Entity
class DatabaseShow (
    @PrimaryKey
    var id             : Long,
    var name           : String,
    @Embedded
    var image          : DatabaseImage,
    var summary        : String,
)

class DatabaseImage(
    val medium: String,
    val original: String
)

fun List<DatabaseShow>.asDomainModel(): List<Show> {
    return map {
        Show(
            id = it.id,
            name = it.name,
            image = Image(
                medium = it.image!!.medium,
                original = it.image!!.original
            ),
            summary = it.summary
        )
    }
}