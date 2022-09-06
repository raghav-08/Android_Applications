package com.ashucode.listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var listView: ListView
    var arrayList = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listview)
        arrayList.add("raghav")
        arrayList.add("Nishu")
        arrayList.add("Didi")
        arrayList.add("papa")
        arrayList.add("maa")
        arrayList.add("avi")
        arrayList.add("shivi")
        arrayList.add("raghav")
        arrayList.add("Nishu")
        arrayList.add("Didi")
        arrayList.add("papa")
        arrayList.add("maa")
        arrayList.add("avi")
        arrayList.add("shivi")
        arrayList.add("raghav")
        arrayList.add("Nishu")
        arrayList.add("Didi")
        arrayList.add("papa")
        arrayList.add("maa")
        arrayList.add("avi")
        arrayList.add("shivi")
        arrayList.add("raghav")
        arrayList.add("Nishu")
        arrayList.add("Didi")
        arrayList.add("papa")
        arrayList.add("maa")
        arrayList.add("avi")
        arrayList.add("shivi")


        var adap = ArrayAdapter<String>(applicationContext, android.R.layout.simple_list_item_1, arrayList)
        listView.adapter = adap

        listView.setOnItemClickListener { adapterView, view, i, l -> if(i==0) Toast.makeText(applicationContext,"hello",Toast.LENGTH_SHORT).show()  }

    }
}

