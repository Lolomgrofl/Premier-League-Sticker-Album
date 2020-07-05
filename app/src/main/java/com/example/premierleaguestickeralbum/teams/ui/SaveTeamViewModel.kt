package com.example.premierleaguestickeralbum.teams.ui

import androidx.lifecycle.ViewModel
import com.example.premierleaguestickeralbum.networking.NetworkClient
import com.example.premierleaguestickeralbum.teams.model.Tim
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SaveTeamViewModel : ViewModel() {

    var teamData: Tim? = null

    fun saveTeam(tim: Tim, successListener: SaveTeamSuccessListener) {
        NetworkClient.getInstance()?.createTeam(tim)?.enqueue(object : Callback<Tim> {

            override fun onFailure(call: Call<Tim>, t: Throwable) {
                successListener.onFailure(t.message.toString())
            }

            override fun onResponse(call: Call<Tim>, response: Response<Tim>) {
                response.body()?.let { successListener.onSuccess() }
            }
        })
    }

    fun deleteTeam(teamId: Long, successListener: DeleteTeamSuccessListener) {
        NetworkClient.getInstance()?.deleteTeam(teamId)?.enqueue(object : Callback<Boolean> {

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                successListener.onFailure(t.message.toString())
            }

            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                response.body()?.let { successListener.onSuccess(it) }
            }
        })
    }

    interface SaveTeamSuccessListener {
        fun onSuccess()
        fun onFailure(message: String)
    }

    interface DeleteTeamSuccessListener {
        fun onSuccess(success: Boolean)
        fun onFailure(message: String)
    }
}