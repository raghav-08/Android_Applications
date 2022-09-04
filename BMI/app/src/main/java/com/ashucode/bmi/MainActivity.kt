package com.ashucode.bmi

import android.content.res.Resources
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi

import androidx.databinding.DataBindingUtil
import com.ashucode.bmi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.btn.setOnClickListener() {
            var weight = Integer.parseInt(binding.etWt.text.toString())
            var feet = Integer.parseInt(binding.etFt.text.toString())
            var inche = Integer.parseInt(binding.etIn.text.toString())

            var totalIn = feet * 12 + inche
            var totalCm = totalIn * 2.53
            var totalM = totalCm / 100
            var bmi = weight / (totalM * totalM)

            if (bmi > 25) {
                binding.MainLayout.setBackgroundColor(getColor(R.color.ow))
                binding.tvResult.setText("You're Overweight!")
            } else if (bmi < 18) {
                binding.tvResult.setText("You're in UnderWeight!")
                binding.MainLayout.setBackgroundColor(getColor(R.color.uw))
            } else {
                binding.tvResult.setText("You're Healthy!")
                binding.MainLayout.setBackgroundColor(getColor(R.color.h))
            }
        }


    }
}