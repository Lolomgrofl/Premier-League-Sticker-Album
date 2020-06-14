package com.example.premierleaguestickeralbum.teams.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.premierleaguestickeralbum.BaseToolbarActivity
import com.example.premierleaguestickeralbum.R
import com.example.premierleaguestickeralbum.teams.model.Tim

class TeamDetailsActivity : BaseToolbarActivity(R.layout.activity_team_details,
    R.string.edit_team_label) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    companion object {
        private const val TEAM_DATA = "team_data"

        fun newInstance(context: Context, team: Tim): Intent {
            val intent = Intent(context, TeamDetailsActivity::class.java)
            intent.putExtra(TEAM_DATA, team)
            return intent
        }
    }
}
