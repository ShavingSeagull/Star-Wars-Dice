package com.example.starwarsdice

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.ViewCompat

class DiceNumberActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dicePick = intent.getStringExtra("faces")
        setContentView(R.layout.activity_dicenumber)
        this.setFinishOnTouchOutside(true)

        var diceNum = 1
        val diceBtnList = ArrayList<Button>()
        val layout = findViewById<ConstraintLayout>(R.id.diceNumberLayout)

        diceNum = when (dicePick) {
            "yellowFaces", "blueFaces", "blackFaces" -> 2
            "greenFaces", "purpleFaces" -> 3
            else -> 1
        }

        for (i in 1..diceNum) {
            val newBtn = Button(this)
            newBtn.id = ViewCompat.generateViewId()
            diceBtnList.add(newBtn)
            layout.addView(newBtn)
        }

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
            prevBtn = btn
        }
        constraintSet.applyTo(layout)
    }
}