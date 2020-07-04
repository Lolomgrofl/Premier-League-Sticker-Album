package com.example.premierleaguestickeralbum.teams.ui

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.premierleaguestickeralbum.BaseToolbarActivity
import com.example.premierleaguestickeralbum.R
import com.example.premierleaguestickeralbum.players.model.Igrac
import com.example.premierleaguestickeralbum.teams.ui.TeamsActivity.Companion.TEAM_KEY
import kotlinx.android.synthetic.main.activity_team_details.*

class TeamDetailsActivity :
    BaseToolbarActivity(R.layout.activity_team_details, R.string.your_teams_label),
    TeamDetailsViewModel.GetPlayersSuccessListener {

    private var viewModel: TeamDetailsViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TeamDetailsViewModel::class.java)

        if (intent.extras != null && intent.extras!!.containsKey(TEAM_KEY)) {
            viewModel?.setData(intent.extras!!.getParcelable(TEAM_KEY))
        }

        supportActionBar?.title = viewModel?.teamData?.ime
        viewModel?.getPlayersList(this)
    }

    override fun onSuccess(players: List<Igrac>) {
        for (player in players) {
            when (player.pozicija) {
                "COACH" -> {
                    coachNameTextView.text = player.ime
                    Glide.with(this).load(player.slikaUrl).into(coachStickerImageView)
                }
                "GK" -> {
                    gkNameTextView.text = player.ime
                    Glide.with(this).load(player.slikaUrl).into(gkStickerImageView)
                }
                "CB1" -> {
                    cb1NameTextView.text = player.ime
                    Glide.with(this).load(player.slikaUrl).into(cb1StickerImageView)
                }
                "CB2" -> {
                    cb2NameTextView.text = player.ime
                    Glide.with(this).load(player.slikaUrl).into(cb2StickerImageView)
                }
                "LB" -> {
                    lbNameTextView.text = player.ime
                    Glide.with(this).load(player.slikaUrl).into(lbStickerImageView)
                }
                "RB" -> {
                    rbNameTextView.text = player.ime
                    Glide.with(this).load(player.slikaUrl).into(rbStickerImageView)
                }
                "CMF1" -> {
                    cmf1NameTextView.text = player.ime
                    Glide.with(this).load(player.slikaUrl).into(cmf1StickerImageView)
                }
                "CMF2" -> {
                    cmf2NameTextView.text = player.ime
                    Glide.with(this).load(player.slikaUrl).into(cmf2StickerImageView)
                }
                "LW" -> {
                    lwNameTextView.text = player.ime
                    Glide.with(this).load(player.slikaUrl).into(lwStickerImageView)
                }
                "RW" -> {
                    rwNameTextView.text = player.ime
                    Glide.with(this).load(player.slikaUrl).into(rwStickerImageView)
                }
                "CF1" -> {
                    cf1NameTextView.text = player.ime
                    Glide.with(this).load(player.slikaUrl).into(cf1StickerImageView)
                }
                "CF2" -> {
                    cf2NameTextView.text = player.ime
                    Glide.with(this).load(player.slikaUrl).into(cf2StickerImageView)
                }
            }
        }
    }

    override fun onFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}