package com.ashucode.rolldice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val image = findViewById<ImageView>(R.id.dice_image)
        val image2 = findViewById<ImageView>(R.id.dice_image2)
        val dicebtn = findViewById<Button>(R.id.dice)
        val reset = findViewById<Button>(R.id.reset)

        dicebtn.setOnClickListener()
        {
            val randomnum = (1..6).random()
            val drwableImage = when(randomnum)
            {
                1 -> R.drawable.dice_1
                2 -> R.drawable.dice_2
                3 -> R.drawable.dice_3
                4 -> R.drawable.dice_4
                5 -> R.drawable.dice_5
                else -> R.drawable.dice_6
            }
            image.setImageResource(drwableImage)

            val randomnum1 = (1..6).random()
            val drwableImage1 = when(randomnum1)
            {
                1 -> R.drawable.dice_1
                2 -> R.drawable.dice_2
                3 -> R.drawable.dice_3
                4 -> R.drawable.dice_4
                5 -> R.drawable.dice_5
                else -> R.drawable.dice_6
            }
            image2.setImageResource(drwableImage1)
        }
        reset.setOnClickListener()
        {
            image.setImageResource(R.drawable.empty_dice)
            image2.setImageResource(R.drawable.empty_dice)
        }
    }
}