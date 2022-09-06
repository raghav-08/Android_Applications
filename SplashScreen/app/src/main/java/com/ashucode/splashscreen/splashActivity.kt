package com.ashucode.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class splashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var intent = Intent(this, MainActivity::class.java)

        Handler().postDelayed(Runnable {
            startActivity(intent)
            finish()
        }, 4000)
    }
}