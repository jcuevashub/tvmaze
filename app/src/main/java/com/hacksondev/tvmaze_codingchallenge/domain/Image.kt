package com.hacksondev.tvmaze_codingchallenge.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Image (
    val medium   : String? = null,
    val original : String? = null

) : Parcelable