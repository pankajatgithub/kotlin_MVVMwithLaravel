package com.example.kotlinmvvmwithlaravel.network

import okhttp3.ResponseBody

sealed class Resource<out T> {
    data class Success<out T>(val  value:T):Resource<T>()
    data class FailureO(
        val isNetworkConected:Boolean,
        val errorCode:Int?,
        val errorBody:ResponseBody?
    ){

    }

}
