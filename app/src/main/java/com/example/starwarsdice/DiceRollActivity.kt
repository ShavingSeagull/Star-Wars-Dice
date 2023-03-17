package com.example.starwarsdice

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.ViewCompat

class DiceRollActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dicePick = intent.getStringExtra("faces")
        val numOfDice = intent.getIntExtra("numOfDice", 1)
        setContentView(R.layout.activity_diceroll)
        this.setFinishOnTouchOutside(false)

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

        val layout = findViewById<ConstraintLayout>(R.id.diceRollLayout)
        val diceImageList = ArrayList<ImageView>()

        // Creates the necessary number of dice programmatically and adds them to the layout
        // Also generates the dice rolls
        for (i in 1..numOfDice) {
            val diceFaceImage = ImageView(this)
            diceFaceImage.id = ViewCompat.generateViewId()
            diceFaceImage.contentDescription = "Dice Face Image"
            when (dicePick) {
                "whiteFaces" -> diceFaceImage.setImageResource(diceRoll(whiteFaces))
                "redFaces" -> diceFaceImage.setImageResource(diceRoll(redFaces))
                "yellowFaces" -> diceFaceImage.setImageResource(diceRoll(yellowFaces))
                "greenFaces" -> diceFaceImage.setImageResource(diceRoll(greenFaces))
                "purpleFaces" -> diceFaceImage.setImageResource(diceRoll(purpleFaces))
                "blueFaces" -> diceFaceImage.setImageResource(diceRoll(blueFaces))
                "blackFaces" -> diceFaceImage.setImageResource(diceRoll(blackFaces))
            }
            diceImageList.add(diceFaceImage)
            layout.addView(diceFaceImage)
        }

        // Adds the new dice image ID's to an array for use with the chainer method below
        val viewIds = IntArray(numOfDice)
        for (img in diceImageList) {
            viewIds[diceImageList.indexOf(img)] = img.id
        }

        // Clones the layout and adds the constraints to the new dice images
        val diceRollBg = findViewById<View>(R.id.diceRollBackground)
        val constraintSet = ConstraintSet()
        constraintSet.clone(layout)

        var prevImg: ImageView? = null

        for (img in diceImageList) {
            val lastItem = diceImageList.indexOf(img) == diceImageList.size - 1
            if (prevImg == null) {
                constraintSet.connect(img.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)
            } else {
                constraintSet.connect(img.id, ConstraintSet.TOP, prevImg.id, ConstraintSet.BOTTOM)
                if (lastItem) {
                    constraintSet.connect(img.id, ConstraintSet.BOTTOM, closeBtn.id, ConstraintSet.TOP)
                }
            }
            if (diceImageList.size == 1) {
                img.setPadding(100,100,100,100)
                constraintSet.connect(img.id, ConstraintSet.BOTTOM, closeBtn.id, ConstraintSet.TOP)
            }
            constraintSet.connect(img.id, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT)
            constraintSet.connect(img.id, ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT)
            prevImg = img
        }
        if (numOfDice > 1) {
            constraintSet.createVerticalChain(
                diceRollBg.id,
                ConstraintSet.TOP,
                closeBtn.id,
                ConstraintSet.TOP,
                viewIds,
                null,
                ConstraintSet.CHAIN_SPREAD
            )
        }
        constraintSet.applyTo(layout)
    }

    /**
     * Generates a random number between 0 and the size of the [faces] array passed in.
     * Uses this number to pick a random element from the array and returns it.
     */
    private fun diceRoll(faces: ArrayList<Int>): Int {
        val roll = (0 until faces.size).random()
        return faces[roll]
    }
}