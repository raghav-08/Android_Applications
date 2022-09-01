package com.ashucode.livedata

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModel : ViewModel() {
    val livedata = MutableLiveData<String>("this is mutable")

    fun updateData()
    {
        livedata.value = ("Data Change")
    }
}