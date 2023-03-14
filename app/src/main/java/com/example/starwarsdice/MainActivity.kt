package com.example.starwarsdice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Disabling the action bar
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
    }
}