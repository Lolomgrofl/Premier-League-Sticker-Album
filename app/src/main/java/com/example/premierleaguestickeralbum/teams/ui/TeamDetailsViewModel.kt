package com.example.premierleaguestickeralbum.teams.ui

import androidx.lifecycle.ViewModel
import com.example.premierleaguestickeralbum.networking.NetworkClient
import com.example.premierleaguestickeralbum.players.model.Igrac
import com.example.premierleaguestickeralbum.teams.model.Tim
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamDetailsViewModel : ViewModel() {

    var teamData: Tim? = null

    fun setData(data: Tim?) {
        teamData = data
    }

    fun getPlayersList(successListener: GetPlayersSuccessListener) {
        NetworkClient.getInstance()?.getPlayers()?.enqueue(object : Callback<List<Igrac>> {
            override fun onFailure(call: Call<List<Igrac>>, t: Throwable) {
                successListener.onFailure(t.message.toString())
            }

            override fun onResponse(call: Call<List<Igrac>>, response: Response<List<Igrac>>) {
                response.body()?.let {
                    val allPlayers = response.body() as List<Igrac>
                    var teamPlayers: MutableList<Igrac> = arrayListOf()
                    for (player in allPlayers) {
                        if (player.timId == teamData?.id) {
                            teamPlayers.add(player)
                        }
                    }
                    successListener.onSuccess(teamPlayers)
                }
            }
        })
    }

    interface GetPlayersSuccessListener {
        fun onSuccess(players: List<Igrac>)
        fun onFailure(message: String)
    }
}