package com.example.submission5.input.presenter

interface InputInterface {

    fun osSuccess(msg : String)
    fun onError(msg : String)
    fun fieldEmpty(msg : String)
}