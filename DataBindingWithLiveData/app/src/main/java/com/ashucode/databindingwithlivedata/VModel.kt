package com.ashucode.databindingwithlivedata

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class VModel : ViewModel() {
    val data = MutableLiveData<String>("Please click on the Button")

    fun updateData()
    {
        data.value = "Text Change!!! Congratulation"
    }
}