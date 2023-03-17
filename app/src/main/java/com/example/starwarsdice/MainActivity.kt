package com.example.starwarsdice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.media.MediaPlayer

class MainActivity : AppCompatActivity() {
    var mMediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Disabling the action bar
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        // Sets click listeners to each die to enable the user to select one and start the
        // dice rolling process.
        val whiteDice = findViewById<ImageView>(R.id.whiteDice)
        whiteDice.setOnClickListener { dicePick("whiteFaces") }
        val redDice = findViewById<ImageView>(R.id.redDice)
        redDice.setOnClickListener { dicePick("redFaces") }
        val yellowDice = findViewById<ImageView>(R.id.yellowDice)
        yellowDice.setOnClickListener { dicePick("yellowFaces") }
        val greenDice = findViewById<ImageView>(R.id.greenDice)
        greenDice.setOnClickListener { dicePick("greenFaces") }
        val purpleDice = findViewById<ImageView>(R.id.purpleDice)
        purpleDice.setOnClickListener { dicePick("purpleFaces") }
        val blueDice = findViewById<ImageView>(R.id.blueDice)
        blueDice.setOnClickListener { dicePick("blueFaces") }
        val blackDice = findViewById<ImageView>(R.id.blackDice)
        blackDice.setOnClickListener { dicePick("blackFaces") }
    }

    /**
     * Takes a set of dice [faces] to pass through to either the dice roll activity or the
     * dice number activity, based on whether the die selected has more than one version in
     * the game (if there are more than one, the user can select how many to roll). Fires a
     * dice roll sound effect for 1.2 seconds before firing the dice roll activity.
     */
    private fun dicePick(faces: String) {
        val singularDice = listOf("whiteFaces", "redFaces")

        if (faces in singularDice) {
            val background = object : Thread() {
                override fun run() {
                    try {
                        if (mMediaPlayer == null) {
                            mMediaPlayer = MediaPlayer.create(baseContext, R.raw.dice_roll)
                            mMediaPlayer!!.isLooping = false
                            mMediaPlayer!!.start()
                        } else mMediaPlayer!!.start()

                        sleep(1200)

                        val intent = Intent(baseContext, DiceRollActivity::class.java)
                        intent.putExtra("faces", faces)
                        startActivity(intent)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
            background.start()
        } else {
            val intent = Intent(this, DiceNumberActivity::class.java)
            intent.putExtra("faces", faces)
            startActivity(intent)
        }
    }

    override fun onStop() {
        super.onStop()
        if (mMediaPlayer != null) {
            mMediaPlayer!!.release()
            mMediaPlayer = null
        }
    }
}