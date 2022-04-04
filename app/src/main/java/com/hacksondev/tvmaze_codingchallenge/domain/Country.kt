package com.hacksondev.tvmaze_codingchallenge.domain
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Country (
  val name     : String? = null,
  val code     : String? = null,
  val timezone : String? = null
) : Parcelable