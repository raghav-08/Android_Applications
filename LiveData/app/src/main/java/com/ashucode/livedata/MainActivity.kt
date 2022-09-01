package com.ashucode.livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: ViewModel
    private val tv: TextView
        get() = findViewById(R.id.tv)
    private val btn: Button
        get() = findViewById(R.id.btn)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(ViewModel::class.java)

        viewModel.livedata.observe(this, Observer {
            tv.text = it
        })
        btn.setOnClickListener()
        {
            viewModel.updateData()
        }
    }
}