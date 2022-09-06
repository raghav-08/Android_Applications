package com.ashucode.animations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var move: Button
    lateinit var alpha: Button
    lateinit var scale: Button
    lateinit var rotate: Button
    lateinit var text: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        move = findViewById(R.id.btn1)
        alpha = findViewById(R.id.btn2)
        scale = findViewById(R.id.btn3)
        rotate = findViewById(R.id.btn4)
        text = findViewById(R.id.txt)

        move.setOnClickListener(){
            var movefile : Animation = AnimationUtils.loadAnimation(applicationContext, R.anim.move)
            text.startAnimation(movefile)
        }
        alpha.setOnClickListener {
            var alphafile = AnimationUtils.loadAnimation(applicationContext, R.anim.aplha)
            text.startAnimation(alphafile)
        }
        rotate.setOnClickListener {
            var rotatefile = AnimationUtils.loadAnimation(applicationContext, R.anim.rotate)
            text.startAnimation(rotatefile)
        }
        scale.setOnClickListener {
            var scalefile = AnimationUtils.loadAnimation(applicationContext, R.anim.scale)
            text.startAnimation(scalefile)
        }
    }
}