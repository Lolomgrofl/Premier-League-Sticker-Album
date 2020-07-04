package com.example.premierleaguestickeralbum.players.ui

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlayersData(
    var timID: Long? = null,
    var coachId: Long? = null,
    var coachName: String = "",
    var coachImageUrl: String = "",
    var gkId: Long? = null,
    var gkName: String = "",
    var gkImageUrl: String = "",
    var lbId: Long? = null,
    var lbName: String = "",
    var lbImageUrl: String = "",
    var rbId: Long? = null,
    var rbName: String = "",
    var rbImageUrl: String = "",
    var cb1Id: Long? = null,
    var cb1Name: String = "",
    var cb1ImageUrl: String = "",
    var cb2Id: Long? = null,
    var cb2Name: String = "",
    var cb2ImageUrl: String = "",
    var cmf1Id: Long? = null,
    var cmf1Name: String = "",
    var cmf1ImageUrl: String = "",
    var cmf2Id: Long? = null,
    var cmf2Name: String = "",
    var cmf2ImageUrl: String = "",
    var lwId: Long? = null,
    var lwName: String = "",
    var lwImageUrl: String = "",
    var rwId: Long? = null,
    var rwName: String = "",
    var rwImageUrl: String = "",
    var cf1Id: Long? = null,
    var cf1Name: String = "",
    var cf1ImageUrl: String = "",
    var cf2Id: Long? = null,
    var cf2Name: String = "",
    var cf2ImageUrl: String = ""
) : Parcelable