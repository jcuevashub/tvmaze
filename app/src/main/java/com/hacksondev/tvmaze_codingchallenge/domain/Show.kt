package com.hacksondev.tvmaze_codingchallenge.domain

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hacksondev.tvmaze_codingchallenge.database.DatabaseImage
import com.hacksondev.tvmaze_codingchallenge.database.DatabaseShow
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Show(
  @PrimaryKey
  val id: Long,
  val name: String,
  val image: Image,
  val summary: String,
  val type: String,
  val language: String,
  val status         : String,
  val runtime        : Int,
  val premiered      : String,
  val weight         : Int,
  val genres         : List<String>,
  val schedule       : Schedule
) : Parcelable

@Parcelize
data class Image(
  val medium: String,
  val original: String
) : Parcelable

@Parcelize
data class Episode (
  val id       : Int,
  val url      : String,
  val name     : String,
  val season   : Int,
  val number   : Int,
  val type     : String,
  val airdate  : String,
  val airtime  : String,
  val airstamp : String,

  val image    : Image,
  val summary  : String,
) : Parcelable


fun List<Show>.asDatabaseModel(): Array<DatabaseShow> {
  return map {
    DatabaseShow(
      id = it.id,
      name = it.name,
      image = DatabaseImage(
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
  }.toTypedArray()
}