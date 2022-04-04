package com.hacksondev.tvmaze_codingchallenge.domain

import android.os.Parcelable
import com.hacksondev.tvmaze_codingchallenge.database.DatabaseImage
import com.hacksondev.tvmaze_codingchallenge.database.DatabaseShow

import kotlinx.android.parcel.Parcelize

@Parcelize
data class Show (
  var id             : Long?             = null,
  var url            : String?           = null,
  var name           : String?           = null,
  var type           : String?           = null,
  var language       : String?           = null,
  var genres         : ArrayList<String> = arrayListOf(),
  var status         : String?           = null,
  var runtime        : Int?              = null,
  var averageRuntime : Int?              = null,
  var premiered      : String?           = null,
  var ended          : String?           = null,
  var officialSite   : String?           = null,
  var schedule       : Schedule?         = Schedule(),
  var rating         : Rating?           = Rating(),
  var weight         : Int?              = null,
  var network        : Network?          = Network(),
  var webChannel     : String?           = null,
  var dvdCountry     : String?           = null,
  var externals      : Externals?        = Externals(),
  var image          : Image?            = Image(),
  var summary        : String?           = null,
  var updated        : Int?              = null,
  var Links          : Links?            = Links()
) : Parcelable

fun List<Show>.asDatabaseModel(): Array<DatabaseShow> {
  return map {
    DatabaseShow(
      id = it.id!!,
      name = it.name!!,
      image = DatabaseImage(
        medium = it.image!!.medium!!,
        original = it.image!!.original!!
      ),
      summary = it.summary!!
    )
  }.toTypedArray()
}