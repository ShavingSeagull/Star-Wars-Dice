package com.example.starwarsdice

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.ViewCompat


class DiceNumberActivity : AppCompatActivity() {
    var mMediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dicePick = intent.getStringExtra("faces")
        setContentView(R.layout.activity_dicenumber)
        this.setFinishOnTouchOutside(true)

        val closeBtn = findViewById<Button>(R.id.closeBtn)
        closeBtn.setOnClickListener { finish() }

        var diceNum = 1
        val diceBtnList = ArrayList<Button>()
        val layout = findViewById<ConstraintLayout>(R.id.diceNumberLayout)

        // Sets the max number of dice that can be chosen, based on game rules
        // Note: white and red are single dice
        diceNum = when (dicePick) {
            "yellowFaces", "blueFaces", "blackFaces" -> 2
            "greenFaces", "purpleFaces" -> 3
            else -> 1
        }

        // Creates the necessary numbers of dice selection buttons programmatically and adds them to the layout
        for (i in 1..diceNum) {
            val newBtn = Button(this)
            newBtn.id = ViewCompat.generateViewId()
            newBtn.text = i.toString()
            newBtn.setPadding(20, 10, 20, 10)
            newBtn.setTextSize(80f)
            diceBtnList.add(newBtn)
            layout.addView(newBtn)
            val param = newBtn.layoutParams as ViewGroup.MarginLayoutParams
            param.setMargins(0, 30, 0, 70)
            newBtn.layoutParams = param
            newBtn.setOnClickListener { numberPick(dicePick, i) }
        }

        // Adds the IDs of the new buttons to an array for use with the chaining method below
        val viewIds = IntArray(diceNum)
        for (btn in diceBtnList) {
            viewIds[diceBtnList.indexOf(btn)] = btn.id
        }

        // Clones the layout and adds the constraints to the new buttons
        val diceBtnConfirmation = findViewById<TextView>(R.id.diceBtnConfirmation)
        val constraintSet = ConstraintSet()
        constraintSet.clone(layout)

        var prevBtn: Button? = null

        for (btn in diceBtnList) {
            val lastItem = diceBtnList.indexOf(btn) == diceBtnList.size - 1
            if (prevBtn == null) {
                constraintSet.connect(btn.id, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT)
            } else {
                constraintSet.connect(btn.id, ConstraintSet.LEFT, prevBtn.id, ConstraintSet.RIGHT)
                if (lastItem) {
                    constraintSet.connect(btn.id, ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT)
                }
            }
            constraintSet.connect(btn.id, ConstraintSet.TOP, diceBtnConfirmation.id, ConstraintSet.BOTTOM)
            constraintSet.connect(btn.id, ConstraintSet.BOTTOM, closeBtn.id, ConstraintSet.TOP)
            prevBtn = btn
        }
        constraintSet.createHorizontalChain(
            ConstraintSet.PARENT_ID,
            ConstraintSet.LEFT,
            ConstraintSet.PARENT_ID,
            ConstraintSet.RIGHT,
            viewIds,
            null,
            ConstraintSet.CHAIN_SPREAD
        )
        constraintSet.applyTo(layout)
    }

    /**
     * Takes in a dice set via the [faces] param, as well as max number of dice that can be
     * selected via [numOfDice]. Starts the dice roll activity, passing those values through.
     * Plays a dice roll sound effect for 1.2 seconds before firing the roll activity.
     */
    private fun numberPick(faces: String?, numOfDice: Int) {
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
                    intent.putExtra("numOfDice", numOfDice)
                    startActivity(intent)
                    finish()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        background.start()
    }

    override fun onStop() {
        super.onStop()
        if (mMediaPlayer != null) {
            mMediaPlayer!!.release()
            mMediaPlayer = null
        }
    }
}