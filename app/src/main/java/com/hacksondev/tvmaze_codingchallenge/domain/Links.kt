package com.hacksondev.tvmaze_codingchallenge.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Links (
  val self            : Self?            = Self(),
  val previousepisode : Previousepisode? = Previousepisode()
) : Parcelable