package com.example.starwarsdice

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class DiceRollActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dicePick = intent.getStringExtra("faces")
        setContentView(R.layout.activity_diceroll)
        this.setFinishOnTouchOutside(true)

        val closeBtn = findViewById<Button>(R.id.closeButton)
        closeBtn.setOnClickListener { finish() }

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

        val diceFaceImage = findViewById<ImageView>(R.id.diceFace)

        // Sets the image to reflect what random face was rolled
        when (dicePick) {
            "whiteFaces" -> diceFaceImage.setImageResource(diceRoll(whiteFaces))
            "redFaces" -> diceFaceImage.setImageResource(diceRoll(redFaces))
            "yellowFaces" -> diceFaceImage.setImageResource(diceRoll(yellowFaces))
            "greenFaces" -> diceFaceImage.setImageResource(diceRoll(greenFaces))
            "purpleFaces" -> diceFaceImage.setImageResource(diceRoll(purpleFaces))
            "blueFaces" -> diceFaceImage.setImageResource(diceRoll(blueFaces))
            "blackFaces" -> diceFaceImage.setImageResource(diceRoll(blackFaces))
        }

    }

    private fun diceRoll(faces: ArrayList<Int>): Int {
        val roll = (0 until faces.size).random()
        return faces[roll]
    }
}