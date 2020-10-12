package com.example.submission5.main.presenter

import com.example.submission5.model.getdata.DataItem

interface MainInterface {
    fun onSuccess (msg : String, data : List<DataItem?>?)
    fun onError (msg : String)
    fun onDelete(msg : String)
}