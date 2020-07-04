package com.example.premierleaguestickeralbum.players.ui

import androidx.lifecycle.ViewModel
import com.example.premierleaguestickeralbum.networking.NetworkClient
import com.example.premierleaguestickeralbum.players.model.Igrac
import com.example.premierleaguestickeralbum.players.model.IgracDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlayerDetailsViewModel : ViewModel() {

    var playerData: IgracDto? = null

    fun setData(player: IgracDto) {
        this.playerData = player
    }

    fun savePlayer(player: IgracDto, successListener: SavePlayerSuccessListener) {
        NetworkClient.getInstance()?.savePlayer(player)?.enqueue(object : Callback<Igrac> {

            override fun onFailure(call: Call<Igrac>, t: Throwable) {
                successListener.onFailure(t.message.toString())
            }

            override fun onResponse(call: Call<Igrac>, response: Response<Igrac>) {
                response.body()?.let { successListener.onSuccess(it) }
            }
        })
    }

    fun deletePlayer(playerId: Long, successListener: DeletePlayerSuccessListener) {
        NetworkClient.getInstance()?.deletePlayer(playerId)?.enqueue(object : Callback<Boolean> {

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                successListener.onFailure(t.message.toString())
            }

            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                response.body()?.let { successListener.onSuccess(it) }
            }
        })
    }

    interface SavePlayerSuccessListener {
        fun onSuccess(player: Igrac)
        fun onFailure(message: String)
    }

    interface DeletePlayerSuccessListener {
        fun onSuccess(success: Boolean)
        fun onFailure(message: String)
    }
}
