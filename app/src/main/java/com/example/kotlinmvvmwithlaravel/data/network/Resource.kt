package com.example.kotlinmvvmwithlaravel.data.network

import okhttp3.ResponseBody

sealed class Resource<out T> {
    data class Success<out T>(val  value:T):Resource<T>()

    data class Failure(
        val isNetworkConected:Boolean,
        val errorCode:Int?,
        val errorBody:ResponseBody?
    ):Resource<Nothing>()

}
