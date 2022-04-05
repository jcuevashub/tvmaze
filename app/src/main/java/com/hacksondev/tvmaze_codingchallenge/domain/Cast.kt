package com.hacksondev.tvmaze_codingchallenge.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cast (
    val person: Person
): Parcelable

@Parcelize
data class Person (
        val id: Int,
        val name: String,
        val image: Image,
        val birthday: String,
        val gender: String,
        val country: Country
) : Parcelable

@Parcelize
data class Country (
    val name     : String,
    val code     : String,
    var timezone : String
): Parcelable