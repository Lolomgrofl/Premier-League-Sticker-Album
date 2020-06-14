package com.example.premierleaguestickeralbum

import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseToolbarActivity(
    @LayoutRes private val contentLayout: Int,
    @StringRes val toolbarTitleRes: Int
) : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentLayout)
        setupToolbar()
    }

    private fun setupToolbar() {
        supportActionBar?.title = getString(toolbarTitleRes)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            onBackPressed()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }
}