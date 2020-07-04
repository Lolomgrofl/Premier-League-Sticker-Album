package com.example.premierleaguestickeralbum.players.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Igrac(
    val id: Long? = null,
    val ime: String,
    val pozicija: String,
    val slikaUrl: String,
    val timId: Long? = null
) : Parcelable
