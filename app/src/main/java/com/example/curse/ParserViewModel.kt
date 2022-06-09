package com.example.curse

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.RequestQueue

class ParserViewModel: ViewModel() {
    val listItems: MutableLiveData<Array<String?>> by lazy {
        MutableLiveData<Array<String?>>()
    }

    fun init(id: String?, requestQueue: RequestQueue?) {
        val service = Parser(id, requestQueue, this)
        service.parse()
    }

    fun update(array: Array<String?>){
        listItems.value = array
    }
}