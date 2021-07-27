package com.example.pagingapplication.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MainResponse(
    val hits: List<Hit>,
    val total: Int,
    val totalHits: Int
) : Parcelable