package com.example.premierleaguestickeralbum.players.model

import android.os.Parcelable
import com.example.premierleaguestickeralbum.teams.model.Tim
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Igrac(
    val id: Long? = null,
    val ime: String,
    val pozicija: String,
    val slikaUrl: String,
    val tim: Tim? = null
) : Parcelable