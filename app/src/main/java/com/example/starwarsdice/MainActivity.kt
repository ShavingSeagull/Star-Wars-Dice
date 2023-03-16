package com.example.starwarsdice

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import androidx.core.view.size

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Disabling the action bar
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        // Dice face lists, in the order of the sprite sheet
        val whiteFaces = arrayListOf(
            R.drawable.white_1_circle_full,
            R.drawable.white_2_circle_empty,
            R.drawable.white_1_circle_full,
            R.drawable.white_2_circle_empty,
            R.drawable.white_1_circle_full,
            R.drawable.white_2_circle_empty,
            R.drawable.white_1_circle_full,
            R.drawable.white_1_circle_empty,
            R.drawable.white_1_circle_full,
            R.drawable.white_1_circle_empty,
            R.drawable.white_1_circle_full,
            R.drawable.white_2_circle_full,
        )

        val redFaces = arrayListOf(
            R.drawable.red_2_flower,
            R.drawable.red_1_flower,
            R.drawable.red_2_flower,
            R.drawable.red_1_flower,
            R.drawable.red_1_triangle_and_1_flower,
            R.drawable.red_1_triangle,
            R.drawable.red_1_triangle_and_1_flower,
            R.drawable.red_1_triangle,
            R.drawable.red_2_triangle,
            R.drawable.red_1_triangle_circle,
            R.drawable.red_2_triangle,
            R.drawable.red_blank,
        )

        val yellowFaces = arrayListOf(
            R.drawable.yellow_2_wings,
            R.drawable.yellow_1_wings,
            R.drawable.yellow_2_wings,
            R.drawable.yellow_1_star_circle,
            R.drawable.yellow_1_star,
            R.drawable.yellow_1_wings_1_star,
            R.drawable.yellow_1_star,
            R.drawable.yellow_1_wings_1_star,
            R.drawable.yellow_2_star,
            R.drawable.yellow_1_wings_1_star,
            R.drawable.yellow_2_star,
            R.drawable.yellow_blank,
        )

        val greenFaces = arrayListOf(
            R.drawable.green_1_star,
            R.drawable.green_1_wings,
            R.drawable.green_1_wings_1_star,
            R.drawable.green_2_stars,
            R.drawable.green_1_wings,
            R.drawable.green_1_star,
            R.drawable.green_2_wings,
            R.drawable.green_blank,
        )

        val purpleFaces = arrayListOf(
            R.drawable.purple_1_flower,
            R.drawable.purple_1_triangle,
            R.drawable.purple_1_flower_1_triangle,
            R.drawable.purple_1_flower,
            R.drawable.purple_blank,
            R.drawable.purple_2_flower,
            R.drawable.purple_2_triangle,
            R.drawable.purple_1_flower,
        )

        val blueFaces = arrayListOf(
            R.drawable.blue_1_star,
            R.drawable.blue_blank,
            R.drawable.blue_2_wings,
            R.drawable.blue_blank,
            R.drawable.blue_1_wings_1_star,
            R.drawable.blue_1_wings,
        )

        val blackFaces = arrayListOf(
            R.drawable.black_1_triangle,
            R.drawable.black_1_triangle,
            R.drawable.black_1_flower,
            R.drawable.black_1_flower,
            R.drawable.black_blank,
            R.drawable.black_blank,
        )

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

    private fun dicePick(faces: String) {
        val diceRollIntent: Intent?
        val singularDice = arrayListOf("whiteFaces", "redFaces")
        diceRollIntent = if (faces in singularDice) {
            Intent(this, DiceRollActivity::class.java)
        } else {
            Intent(this, DiceNumberActivity::class.java)
        }
        diceRollIntent.putExtra("faces", faces)
        startActivity(diceRollIntent)
    }
}