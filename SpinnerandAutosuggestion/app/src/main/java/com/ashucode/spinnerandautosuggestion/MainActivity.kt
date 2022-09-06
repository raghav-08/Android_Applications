package com.ashucode.spinnerandautosuggestion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var spinner: Spinner
    lateinit var autotxt: AutoCompleteTextView

    var arrspinner = ArrayList<String>()
    var arrAuto = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinner = findViewById(R.id.spinner)
        autotxt = findViewById(R.id.txtAuto)

        arrspinner.add("Select your ID Proof")
        arrspinner.add("Aadhaar card")
        arrspinner.add("pan card")
        arrspinner.add("Ration card")
        arrspinner.add("Voter card")
        arrspinner.add("Driving licence")

        var adapterspinner = ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, arrspinner)
        spinner.adapter = adapterspinner


        arrAuto.add("Objective C")
        arrAuto.add("C++")
        arrAuto.add("C#")
        arrAuto.add("Java")
        arrAuto.add("Python")
        arrAuto.add("JavaScript")

        var adapterAuto = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrAuto)
        autotxt.setAdapter(adapterAuto)
    }
}