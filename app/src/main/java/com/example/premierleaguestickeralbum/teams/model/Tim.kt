package com.example.premierleaguestickeralbum.teams.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Tim(
    val id: Long? = null,
    val ime: String,
    val grbUrl: String,
    val brojSlicica: Int = 0
) : Parcelable