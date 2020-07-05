package com.example.premierleaguestickeralbum.teams.ui

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.example.premierleaguestickeralbum.BaseToolbarActivity
import com.example.premierleaguestickeralbum.R
import com.example.premierleaguestickeralbum.teams.model.Tim
import com.example.premierleaguestickeralbum.teams.ui.TeamsActivity.Companion.CREATE_TEAM_REQUEST_CODE
import com.example.premierleaguestickeralbum.teams.ui.TeamsActivity.Companion.TEAM_KEY
import kotlinx.android.synthetic.main.activity_create_team.*

class SaveTeamActivity :
    BaseToolbarActivity(R.layout.activity_create_team, R.string.create_team_label),
    SaveTeamViewModel.SaveTeamSuccessListener, SaveTeamViewModel.DeleteTeamSuccessListener {

    private var viewModel: SaveTeamViewModel? = null
    private var isCreateMode: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SaveTeamViewModel::class.java)
        getExtras()
        initViews()

        saveButton.setOnClickListener {
            val tim = Tim(
                id = viewModel?.teamData?.id,
                ime = nameEditText.text.toString(),
                grbUrl = logoEditText.text.toString()
            )
            viewModel?.saveTeam(tim, this)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.delete_menu, menu)
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            R.id.optionDelete -> {
                val alertDialogBuilder = AlertDialog.Builder(this)
                alertDialogBuilder.setTitle(getString(R.string.delete_team_dialog_title))
                alertDialogBuilder.setMessage(getString(R.string.delete_team_dialog_message))
                alertDialogBuilder.setPositiveButton(
                    getString(R.string.positive_button_text),
                    DialogInterface.OnClickListener { dialog, which ->
                        run {
                            viewModel?.teamData?.id?.let { viewModel?.deleteTeam(it, this) }
                        }
                    })
                alertDialogBuilder.setNegativeButton(
                    getString(R.string.negative_button_text),
                    DialogInterface.OnClickListener { dialog, which ->
                        run {
                            dialog.dismiss()
                        }
                    })
                alertDialogBuilder.show()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onSuccess() {
        Toast.makeText(this, getString(R.string.create_team_successfull_message), Toast.LENGTH_LONG)
            .show()
        finish()
        setResult(CREATE_TEAM_REQUEST_CODE)
    }

    override fun onSuccess(success: Boolean) {
        if (success) {
            Toast.makeText(
                this, getString(R.string.delete_team_success_message),
                Toast.LENGTH_LONG
            ).show()
            setResult(TeamDetailsActivity.SAVE_PLAYER_REQUEST_CODE)
            finish()
        }
    }

    override fun onFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun getExtras() {
        if (intent.extras != null) {
            if (intent.extras!!.containsKey(TEAM_KEY)) {
                viewModel?.teamData = intent.extras!!.getParcelable(TEAM_KEY)
            }
            if (intent.extras!!.containsKey(IS_CREATE_FLOW)) {
                isCreateMode = intent.extras!!.getBoolean(IS_CREATE_FLOW)
            }

        }
    }

    private fun initViews() {
        if (isCreateMode) {
            supportActionBar?.title = getString(R.string.create_team_label)
            saveButton.text = getString(R.string.create_button_text)
        } else {
            supportActionBar?.title = viewModel?.teamData?.ime
            saveButton.text = getString(R.string.save_button_text)
        }
        nameEditText.setText(viewModel?.teamData?.ime)
        logoEditText.setText(viewModel?.teamData?.grbUrl)
    }

    companion object {
        const val IS_CREATE_FLOW = "is_create_flow"
    }
}
