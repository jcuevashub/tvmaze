package com.hacksondev.tvmaze_codingchallenge.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hacksondev.tvmaze_codingchallenge.domain.Image
import com.hacksondev.tvmaze_codingchallenge.domain.Schedule
import com.hacksondev.tvmaze_codingchallenge.domain.Show
import java.util.*

@Entity
class DatabaseShow(
    @PrimaryKey val id: Long,
    val name: String,
    @Embedded
    val image: DatabaseImage,
    val summary: String,
    val type: String,
    val language: String,
    val status         : String,
    val runtime        : Int,
    val premiered      : String,
    val weight         : Int,
    val genres: List<String>,
    val schedule: Schedule
)

class DatabaseImage(
    var medium: String,
    var original: String
)

class Converters {

    @TypeConverter
    fun listToJson(value: List<String>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()

    @TypeConverter
    fun scheduleToString(schedule: Schedule): String {
        return Gson().toJson(schedule)
    }

    @TypeConverter
    fun stringToSchedule(recipeString: String): Schedule {
        val objectType = object : TypeToken<Schedule>() {}.type
        return Gson().fromJson(recipeString, objectType)
    }
}

fun List<DatabaseShow>.asDomainModel(): List<Show> {
    return map {
        Show(
            id = it.id,
            name = it.name,
            image = Image(
                medium = it.image.medium,
                original = it.image.original
            ),
            summary = it.summary,
            type = it.type,
            language = it.language,
            status = it.status,
            runtime = it.runtime,
            premiered = it.premiered,
            weight = it.weight,
            genres = it.genres,
            schedule = it.schedule
        )
    }
}