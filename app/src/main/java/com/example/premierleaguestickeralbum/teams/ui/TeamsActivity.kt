package com.example.premierleaguestickeralbum.teams.ui

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.premierleaguestickeralbum.BaseToolbarActivity
import com.example.premierleaguestickeralbum.R
import com.example.premierleaguestickeralbum.teams.viewmodel.TeamsViewModel
import com.example.premierleaguestickeralbum.teams.model.Tim
import kotlinx.android.synthetic.main.activity_teams.*

class TeamsActivity : BaseToolbarActivity(R.layout.activity_teams, R.string.your_teams_label),
    TeamsViewModel.SuccessListener {

    private var viewModel: TeamsViewModel? = null
    private lateinit var teamsAdapter: TeamsAdapter

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        initRecyclerView()

        viewModel = ViewModelProviders.of(this).get(TeamsViewModel::class.java)
        viewModel?.getTeams(this)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_new_menu, menu)
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            R.id.optionAdd -> {
                showCreateTeamActivity()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onSuccess(teams: List<Tim>) {
        teamsAdapter.updateData(teams)
    }

    override fun onFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun showCreateTeamActivity() {
        val intent = Intent(this, CreateTeamActivity::class.java)
        startActivity(intent)
    }

    private fun initRecyclerView() {
        teamsRecyclerView.layoutManager = GridLayoutManager(this, 2)
        teamsRecyclerView.setHasFixedSize(true)
        teamsAdapter =
            TeamsAdapter(this)
        teamsRecyclerView.adapter = teamsAdapter

        teamsAdapter.setOnClickListener(object :
            TeamsAdapter.TeamClickListener {
            override fun onTeamClicked(team: Tim) {
                startActivity(TeamDetailsActivity.newInstance(this@TeamsActivity, team))
            }
        })
    }
}
