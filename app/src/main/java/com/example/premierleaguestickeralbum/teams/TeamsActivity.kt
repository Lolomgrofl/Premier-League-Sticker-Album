package com.example.premierleaguestickeralbum.teams

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.premierleaguestickeralbum.R
import com.example.premierleaguestickeralbum.teams.model.Tim
import kotlinx.android.synthetic.main.activity_teams.*

class TeamsActivity : AppCompatActivity(), TeamsViewModel.SuccessListener {

    private var viewModel: TeamsViewModel? = null
    private lateinit var teamsAdapter: TeamsAdapter

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        setContentView(R.layout.activity_teams)
        initRecyclerView()

        viewModel = ViewModelProviders.of(this).get(TeamsViewModel::class.java)
        viewModel?.getTeams(this)
    }

    override fun onSuccess(teams: List<Tim>) {
        teamsAdapter.updateData(teams)
    }

    override fun onFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun initRecyclerView() {
        teamsRecyclerView.layoutManager = GridLayoutManager(this, 2)
        teamsRecyclerView.setHasFixedSize(true)
        teamsAdapter = TeamsAdapter(this)
        teamsRecyclerView.adapter = teamsAdapter
    }
}
