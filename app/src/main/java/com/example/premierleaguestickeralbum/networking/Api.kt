package com.example.premierleaguestickeralbum.networking

import com.example.premierleaguestickeralbum.teams.model.Tim
import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @GET("tim/returnAll")
    fun getTeams(): Call<List<Tim>>
}