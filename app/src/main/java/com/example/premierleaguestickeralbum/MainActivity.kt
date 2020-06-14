package com.example.premierleaguestickeralbum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.premierleaguestickeralbum.teams.ui.TeamsActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        logoImageView.setOnClickListener {
            showTeamsActivity()
        }

        welcomeTextView.setOnClickListener {
            showTeamsActivity()
        }
    }

    private fun showTeamsActivity() {
        val intent = Intent(this, TeamsActivity::class.java)
        startActivity(intent)
    }
}
