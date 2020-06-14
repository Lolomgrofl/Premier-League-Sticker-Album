package com.example.premierleaguestickeralbum.teams.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.premierleaguestickeralbum.R
import com.example.premierleaguestickeralbum.teams.model.Tim

class TeamsAdapter(private val context: Context) : RecyclerView.Adapter<TeamsAdapter.TeamsViewHolder>() {

    private lateinit var teams: List<Tim>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            TeamsViewHolder =
        TeamsViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.team_list_item, parent, false)
        )


    override fun getItemCount(): Int {
        return teams.size
    }

    override fun onBindViewHolder(holder: TeamsViewHolder, position: Int) {
        holder.bind(context, teams[position])
    }

    fun updateData(teams: List<Tim>) {
        this.teams = teams
        notifyDataSetChanged()
    }

    class TeamsViewHolder constructor(item: View) : RecyclerView.ViewHolder(item) {
        private var logo: ImageView = item.findViewById(R.id.logoImageView)
        private var name: TextView? = item.findViewById(R.id.teamNameTextView)
        private var stickerCounter: TextView? = item.findViewById(R.id.stickerCounterTextView)

        @SuppressLint("SetTextI18n")
        fun bind(context: Context, team: Tim) {
            name?.text = team.ime
            stickerCounter?.text = team.brojSlicica.toString() + "/12"
            Glide.with(context).load(team.grbUrl).into(logo)
        }
    }
}