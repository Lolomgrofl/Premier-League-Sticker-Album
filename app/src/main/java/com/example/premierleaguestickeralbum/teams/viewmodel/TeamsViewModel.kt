package com.example.premierleaguestickeralbum.teams.viewmodel

import androidx.lifecycle.ViewModel
import com.example.premierleaguestickeralbum.networking.NetworkClient
import com.example.premierleaguestickeralbum.teams.model.Tim
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamsViewModel : ViewModel() {

    fun getTeams(successListener: SuccessListener) {
        NetworkClient.getInstance()?.getTeams()?.enqueue(object : Callback<List<Tim>> {
            override fun onFailure(call: Call<List<Tim>>, t: Throwable) {
                successListener.onFailure(t.message.toString())
            }

            override fun onResponse(call: Call<List<Tim>>, response: Response<List<Tim>>) {
                response.body()?.let { successListener.onSuccess(it) }
            }
        })
    }

    interface SuccessListener {
        fun onSuccess(teams: List<Tim>)
        fun onFailure(message: String)
    }
}