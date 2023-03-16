package com.example.starwarsdice

import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
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

        val closeBtn = findViewById<Button>(R.id.closeBtn)
        closeBtn.setOnClickListener { finish() }

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
            newBtn.text = i.toString()
            newBtn.setPadding(20, 10, 20, 10)
            newBtn.setTextSize(80f)
            newBtn.requestLayout()
            diceBtnList.add(newBtn)
            layout.addView(newBtn)
            val param = newBtn.layoutParams as ViewGroup.MarginLayoutParams
            param.setMargins(0, 30, 0, 70)
            newBtn.layoutParams = param
        }

        val viewIds = IntArray(diceNum)
        for (btn in diceBtnList) {
            viewIds[diceBtnList.indexOf(btn)] = btn.id
        }

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
}