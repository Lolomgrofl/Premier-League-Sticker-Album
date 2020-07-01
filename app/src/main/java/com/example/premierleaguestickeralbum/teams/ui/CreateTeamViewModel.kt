package com.example.premierleaguestickeralbum.teams.ui

import androidx.lifecycle.ViewModel
import com.example.premierleaguestickeralbum.networking.NetworkClient
import com.example.premierleaguestickeralbum.teams.model.Tim
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateTeamViewModel : ViewModel() {

    fun createTeam(tim: Tim, successListener: CreateTeamSuccessListener) {
        NetworkClient.getInstance()?.createTeam(tim)?.enqueue(object : Callback<Tim> {

            override fun onFailure(call: Call<Tim>, t: Throwable) {
                successListener.onFailure(t.message.toString())
            }

            override fun onResponse(call: Call<Tim>, response: Response<Tim>) {
                response.body()?.let { successListener.onSuccess() }
            }
        })
    }

    interface CreateTeamSuccessListener {
        fun onSuccess()
        fun onFailure(message: String)
    }
}