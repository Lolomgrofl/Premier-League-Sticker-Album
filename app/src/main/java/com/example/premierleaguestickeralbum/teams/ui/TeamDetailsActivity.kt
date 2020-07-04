package com.example.premierleaguestickeralbum.teams.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.premierleaguestickeralbum.BaseToolbarActivity
import com.example.premierleaguestickeralbum.R
import com.example.premierleaguestickeralbum.players.model.Igrac
import com.example.premierleaguestickeralbum.players.model.IgracDto
import com.example.premierleaguestickeralbum.players.ui.PlayerDetailsActivity
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
        initListeners()
    }

    override fun onSuccess(players: List<Igrac>) {
        for (player in players) {
            viewModel?.playersData?.timID = player.tim?.id
            when (player.pozicija) {
                "COACH" -> {
                    coachNameTextView.text = player.ime
                    Glide.with(this).load(player.slikaUrl).into(coachStickerImageView)
                    viewModel?.playersData?.coachId = player.id
                    viewModel?.playersData?.coachName = player.ime
                    viewModel?.playersData?.coachImageUrl = player.slikaUrl

                }
                "GK" -> {
                    gkNameTextView.text = player.ime
                    Glide.with(this).load(player.slikaUrl).into(gkStickerImageView)
                    viewModel?.playersData?.gkId = player.id
                    viewModel?.playersData?.gkName = player.ime
                    viewModel?.playersData?.gkImageUrl = player.slikaUrl
                }
                "CB1" -> {
                    cb1NameTextView.text = player.ime
                    Glide.with(this).load(player.slikaUrl).into(cb1StickerImageView)
                    viewModel?.playersData?.cb1Id = player.id
                    viewModel?.playersData?.cb1Name = player.ime
                    viewModel?.playersData?.cb1ImageUrl = player.slikaUrl
                }
                "CB2" -> {
                    cb2NameTextView.text = player.ime
                    Glide.with(this).load(player.slikaUrl).into(cb2StickerImageView)
                    viewModel?.playersData?.cb2Id = player.id
                    viewModel?.playersData?.cb2Name = player.ime
                    viewModel?.playersData?.cb2ImageUrl = player.slikaUrl
                }
                "LB" -> {
                    lbNameTextView.text = player.ime
                    Glide.with(this).load(player.slikaUrl).into(lbStickerImageView)
                    viewModel?.playersData?.lbId = player.id
                    viewModel?.playersData?.lbName = player.ime
                    viewModel?.playersData?.lbImageUrl = player.slikaUrl
                }
                "RB" -> {
                    rbNameTextView.text = player.ime
                    Glide.with(this).load(player.slikaUrl).into(rbStickerImageView)
                    viewModel?.playersData?.rbId = player.id
                    viewModel?.playersData?.rbName = player.ime
                    viewModel?.playersData?.rbImageUrl = player.slikaUrl
                }
                "CMF1" -> {
                    cmf1NameTextView.text = player.ime
                    Glide.with(this).load(player.slikaUrl).into(cmf1StickerImageView)
                    viewModel?.playersData?.cmf1Id = player.id
                    viewModel?.playersData?.cmf1Name = player.ime
                    viewModel?.playersData?.cmf1ImageUrl = player.slikaUrl
                }
                "CMF2" -> {
                    cmf2NameTextView.text = player.ime
                    Glide.with(this).load(player.slikaUrl).into(cmf2StickerImageView)
                    viewModel?.playersData?.cmf2Id = player.id
                    viewModel?.playersData?.cmf2Name = player.ime
                    viewModel?.playersData?.cmf2ImageUrl = player.slikaUrl
                }
                "LW" -> {
                    lwNameTextView.text = player.ime
                    Glide.with(this).load(player.slikaUrl).into(lwStickerImageView)
                    viewModel?.playersData?.lwId = player.id
                    viewModel?.playersData?.lwName = player.ime
                    viewModel?.playersData?.lwImageUrl = player.slikaUrl
                }
                "RW" -> {
                    rwNameTextView.text = player.ime
                    Glide.with(this).load(player.slikaUrl).into(rwStickerImageView)
                    viewModel?.playersData?.rwId = player.id
                    viewModel?.playersData?.rwName = player.ime
                    viewModel?.playersData?.rwImageUrl = player.slikaUrl
                }
                "CF1" -> {
                    cf1NameTextView.text = player.ime
                    Glide.with(this).load(player.slikaUrl).into(cf1StickerImageView)
                    viewModel?.playersData?.cf1Id = player.id
                    viewModel?.playersData?.cf1Name = player.ime
                    viewModel?.playersData?.cf1ImageUrl = player.slikaUrl
                }
                "CF2" -> {
                    cf2NameTextView.text = player.ime
                    Glide.with(this).load(player.slikaUrl).into(cf2StickerImageView)
                    viewModel?.playersData?.cf2Id = player.id
                    viewModel?.playersData?.cf2Name = player.ime
                    viewModel?.playersData?.cf2ImageUrl = player.slikaUrl
                }
            }
        }
    }

    override fun onFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun initListeners() {
        coachLayout.setOnClickListener {
            val intent = Intent(this, PlayerDetailsActivity::class.java)
            intent.putExtra(
                PLAYER_KEY, IgracDto(
                    id = viewModel?.playersData?.coachId,
                    ime = viewModel?.playersData?.coachName.toString(),
                    slikaUrl = viewModel?.playersData?.coachImageUrl.toString(),
                    pozicija = "COACH",
                    timId = viewModel?.playersData?.timID!!
                )
            )
            startActivity(intent)
        }

        gkLayout.setOnClickListener {
            val intent = Intent(this, PlayerDetailsActivity::class.java)
            intent.putExtra(
                PLAYER_KEY, IgracDto(
                    id = viewModel?.playersData?.gkId,
                    ime = viewModel?.playersData?.gkName.toString(),
                    slikaUrl = viewModel?.playersData?.gkImageUrl.toString(),
                    pozicija = "GK",
                    timId = viewModel?.playersData?.timID
                )
            )
            startActivity(intent)
        }

        lbLayout.setOnClickListener {
            val intent = Intent(this, PlayerDetailsActivity::class.java)
            intent.putExtra(
                PLAYER_KEY, IgracDto(
                    id = viewModel?.playersData?.lbId,
                    ime = viewModel?.playersData?.lbName.toString(),
                    slikaUrl = viewModel?.playersData?.lbImageUrl.toString(),
                    pozicija = "LB",
                    timId = viewModel?.playersData?.timID
                )
            )
            startActivity(intent)
        }

        cb1Layout.setOnClickListener {
            val intent = Intent(this, PlayerDetailsActivity::class.java)
            intent.putExtra(
                PLAYER_KEY, IgracDto(
                    id = viewModel?.playersData?.cb1Id,
                    ime = viewModel?.playersData?.cb1Name.toString(),
                    slikaUrl = viewModel?.playersData?.cb1ImageUrl.toString(),
                    pozicija = "CB1",
                    timId = viewModel?.playersData?.timID
                )
            )
            startActivity(intent)
        }

        cb2Layout.setOnClickListener {
            val intent = Intent(this, PlayerDetailsActivity::class.java)
            intent.putExtra(
                PLAYER_KEY, IgracDto(
                    id = viewModel?.playersData?.cb2Id,
                    ime = viewModel?.playersData?.cb2Name.toString(),
                    slikaUrl = viewModel?.playersData?.cb2ImageUrl.toString(),
                    pozicija = "CB2",
                    timId = viewModel?.playersData?.timID
                )
            )
            startActivity(intent)
        }

        rbLayout.setOnClickListener {
            val intent = Intent(this, PlayerDetailsActivity::class.java)
            intent.putExtra(
                PLAYER_KEY, IgracDto(
                    id = viewModel?.playersData?.rbId,
                    ime = viewModel?.playersData?.rbName.toString(),
                    slikaUrl = viewModel?.playersData?.rbImageUrl.toString(),
                    pozicija = "RB",
                    timId = viewModel?.playersData?.timID
                )
            )
            startActivity(intent)
        }

        cmf1Layout.setOnClickListener {
            val intent = Intent(this, PlayerDetailsActivity::class.java)
            intent.putExtra(
                PLAYER_KEY, IgracDto(
                    id = viewModel?.playersData?.cmf1Id,
                    ime = viewModel?.playersData?.cmf1Name.toString(),
                    slikaUrl = viewModel?.playersData?.cmf1ImageUrl.toString(),
                    pozicija = "CMF1",
                    timId = viewModel?.playersData?.timID
                )
            )
            startActivity(intent)
        }

        cmf2Layout.setOnClickListener {
            val intent = Intent(this, PlayerDetailsActivity::class.java)
            intent.putExtra(
                PLAYER_KEY, IgracDto(
                    id = viewModel?.playersData?.cmf2Id,
                    ime = viewModel?.playersData?.cmf2Name.toString(),
                    slikaUrl = viewModel?.playersData?.cmf2ImageUrl.toString(),
                    pozicija = "CMF2",
                    timId = viewModel?.playersData?.timID
                )
            )
            startActivity(intent)
        }

        lwLayout.setOnClickListener {
            val intent = Intent(this, PlayerDetailsActivity::class.java)
            intent.putExtra(
                PLAYER_KEY, IgracDto(
                    id = viewModel?.playersData?.lwId,
                    ime = viewModel?.playersData?.lwName.toString(),
                    slikaUrl = viewModel?.playersData?.lwImageUrl.toString(),
                    pozicija = "LW",
                    timId = viewModel?.playersData?.timID
                )
            )
            startActivity(intent)
        }

        rwLayout.setOnClickListener {
            val intent = Intent(this, PlayerDetailsActivity::class.java)
            intent.putExtra(
                PLAYER_KEY, IgracDto(
                    id = viewModel?.playersData?.rwId,
                    ime = viewModel?.playersData?.rwName.toString(),
                    slikaUrl = viewModel?.playersData?.rwImageUrl.toString(),
                    pozicija = "RW",
                    timId = viewModel?.playersData?.timID
                )
            )
            startActivity(intent)
        }

        cf1Layout.setOnClickListener {
            val intent = Intent(this, PlayerDetailsActivity::class.java)
            intent.putExtra(
                PLAYER_KEY, IgracDto(
                    id = viewModel?.playersData?.cf1Id,
                    ime = viewModel?.playersData?.cf1Name.toString(),
                    slikaUrl = viewModel?.playersData?.cf1ImageUrl.toString(),
                    pozicija = "CF1",
                    timId = viewModel?.playersData?.timID
                )
            )
            startActivity(intent)
        }

        cf2Layout.setOnClickListener {
            val intent = Intent(this, PlayerDetailsActivity::class.java)
            intent.putExtra(
                PLAYER_KEY, IgracDto(
                    id = viewModel?.playersData?.cf2Id,
                    ime = viewModel?.playersData?.cf2Name.toString(),
                    slikaUrl = viewModel?.playersData?.cf2ImageUrl.toString(),
                    pozicija = "CF2",
                    timId = viewModel?.playersData?.timID
                )
            )
            startActivity(intent)
        }
    }

    companion object {
        const val PLAYER_KEY = "player_key"
    }
}