package com.example.premierleaguestickeralbum.teams.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Tim(
    val id: Long,
    val ime: String,
    val grbUrl: String,
    val brojSlicica: Int
) : Parcelable