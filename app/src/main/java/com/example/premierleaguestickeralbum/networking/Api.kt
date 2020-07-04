package com.example.premierleaguestickeralbum.networking

import com.example.premierleaguestickeralbum.players.model.Igrac
import com.example.premierleaguestickeralbum.players.model.IgracDto
import com.example.premierleaguestickeralbum.teams.model.Tim
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {

    @GET("tim/returnAll")
    fun getTeams(): Call<List<Tim>>

    @POST("tim/save")
    fun createTeam(@Body tim: Tim): Call<Tim>

    @GET("igrac/returnAll")
    fun getPlayers(): Call<List<Igrac>>

    @POST("igrac/save")
    fun savePlayer(@Body igracDto: IgracDto): Call<Igrac>
}