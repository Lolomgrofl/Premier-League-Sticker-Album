package com.example.premierleaguestickeralbum.players.ui

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.example.premierleaguestickeralbum.BaseToolbarActivity
import com.example.premierleaguestickeralbum.R
import com.example.premierleaguestickeralbum.players.model.Igrac
import com.example.premierleaguestickeralbum.players.model.IgracDto
import com.example.premierleaguestickeralbum.teams.ui.TeamDetailsActivity.Companion.PLAYER_KEY
import com.example.premierleaguestickeralbum.teams.ui.TeamDetailsActivity.Companion.SAVE_PLAYER_REQUEST_CODE
import kotlinx.android.synthetic.main.activity_player_details.*

class PlayerDetailsActivity :
    BaseToolbarActivity(R.layout.activity_player_details, R.string.your_teams_label),
    PlayerDetailsViewModel.SavePlayerSuccessListener,
    PlayerDetailsViewModel.DeletePlayerSuccessListener {

    private var viewModel: PlayerDetailsViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PlayerDetailsViewModel::class.java)
        getExtras()

        supportActionBar?.title = "Pozicija: " + viewModel?.playerData?.pozicija
        initViews()
        initListeners()
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
                alertDialogBuilder.setTitle(getString(R.string.delete_player_dialog_title))
                alertDialogBuilder.setMessage(getString(R.string.delete_player_dialog_message))
                alertDialogBuilder.setPositiveButton(getString(R.string.positive_button_text), DialogInterface.OnClickListener { dialog, which ->
                    run {
                        viewModel?.playerData?.id?.let { viewModel?.deletePlayer(it, this) }
                    }
                })
                alertDialogBuilder.setNegativeButton(getString(R.string.negative_button_text), DialogInterface.OnClickListener { dialog, which ->
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

    override fun onSuccess(player: Igrac) {
        Toast.makeText(this, getString(R.string.create_player_succes_message),
            Toast.LENGTH_LONG).show()
            finish()
        setResult(SAVE_PLAYER_REQUEST_CODE)
    }

    override fun onSuccess(success: Boolean) {
        if (success) {
            Toast.makeText(this, getString(R.string.delete_player_success_message),
                Toast.LENGTH_LONG).show()
            setResult(SAVE_PLAYER_REQUEST_CODE)
            finish()
        }
    }

    override fun onFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun getExtras() {
        if (intent.extras != null) {
            if (intent.extras!!.containsKey(PLAYER_KEY)) {
                val player = intent.extras!!.getParcelable<IgracDto>(PLAYER_KEY)!!
                viewModel?.setData(
                    IgracDto(
                        id = player.id,
                        ime = player.ime,
                        slikaUrl = player.slikaUrl,
                        pozicija = player.pozicija,
                        timId = player.timId
                    )
                )
            }
        }
    }

    private fun initViews() {
        nameEditText.setText(viewModel?.playerData?.ime)
        stickerEditText.setText(viewModel?.playerData?.slikaUrl)

        if (viewModel?.playerData?.ime.isNullOrEmpty() &&
            viewModel?.playerData?.slikaUrl.isNullOrEmpty()
        ) {
            saveButton.text = getString(R.string.create_player_button_text)
        } else {
            saveButton.text = getString(R.string.update_player_button_text)
        }
    }

    private fun initListeners() {
        saveButton.setOnClickListener {
           viewModel?.savePlayer(getInputValues(), this)
        }
    }

    private fun getInputValues(): IgracDto {
        return IgracDto(
            id = viewModel?.playerData?.id,
            ime = nameEditText.text.toString(),
            pozicija = viewModel?.playerData?.pozicija!!,
            slikaUrl = stickerEditText.text.toString(),
            timId = viewModel?.playerData?.timId
        )
    }
}
