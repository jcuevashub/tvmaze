package com.hacksondev.tvmaze_codingchallenge.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Externals (
  val tvrage  : Int?    = null,
  val thetvdb : Int?    = null,
  val imdb    : String? = null
) : Parcelable