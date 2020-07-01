package com.example.premierleaguestickeralbum.teams.ui

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.example.premierleaguestickeralbum.BaseToolbarActivity
import com.example.premierleaguestickeralbum.R
import com.example.premierleaguestickeralbum.teams.model.Tim
import com.example.premierleaguestickeralbum.teams.ui.TeamsActivity.Companion.CREATE_TEAM_REQUEST_CODE
import com.example.premierleaguestickeralbum.teams.viewmodel.TeamsViewModel
import kotlinx.android.synthetic.main.activity_create_team.*

class CreateTeamActivity :
    BaseToolbarActivity(R.layout.activity_create_team, R.string.create_team_label),
    CreateTeamViewModel.CreateTeamSuccessListener {

    private var viewModel: CreateTeamViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(CreateTeamViewModel::class.java)

        createButton.setOnClickListener {
            val tim = Tim(
                ime = nameEditText.text.toString(),
                grbUrl = logoEditText.text.toString()
            )
            viewModel?.createTeam(tim, this)
        }
    }

    override fun onSuccess() {
        Toast.makeText(this, getString(R.string.create_team_successfull_message), Toast.LENGTH_LONG).show()
        finish()
        setResult(CREATE_TEAM_REQUEST_CODE)
    }

    override fun onFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
