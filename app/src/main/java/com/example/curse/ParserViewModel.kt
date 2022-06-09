package com.example.curse

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ParserViewModel: ViewModel() {
    val listItems: MutableLiveData<Array<String?>> by lazy {
        MutableLiveData<Array<String?>>()
    }

    fun update(array: Array<String?>){
        listItems.value = array
    }
}