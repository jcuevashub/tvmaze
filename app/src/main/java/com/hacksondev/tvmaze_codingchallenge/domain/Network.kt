package com.hacksondev.tvmaze_codingchallenge.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Network (
  val id           : Int?     = null,
  val name         : String?  = null,
  val country      : Country? = Country(),
  val officialSite : String?  = null
) : Parcelable