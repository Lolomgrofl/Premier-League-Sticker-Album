package com.example.premierleaguestickeralbum.teams.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
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
    TeamsViewModel.GetTeamsSuccessListener {

    private var viewModel: TeamsViewModel? = null
    private lateinit var teamsAdapter: TeamsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CREATE_TEAM_REQUEST_CODE) {
            viewModel?.getTeams(this)
        }
    }

    private fun showCreateTeamActivity() {
        val intent = Intent(this, CreateTeamActivity::class.java)
        startActivityForResult(intent, CREATE_TEAM_REQUEST_CODE)
    }

    private fun initRecyclerView() {
        teamsRecyclerView.layoutManager = GridLayoutManager(this, 2)
        teamsRecyclerView.setHasFixedSize(true)
        teamsAdapter = TeamsAdapter(this)
        teamsRecyclerView.adapter = teamsAdapter
    }

    companion object {
        val CREATE_TEAM_REQUEST_CODE = 1
    }
}
