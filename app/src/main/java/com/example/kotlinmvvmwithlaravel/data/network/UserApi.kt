package com.example.kotlinmvvmwithlaravel.data.network

import com.example.kotlinmvvmwithlaravel.data.responses.LoginResponse
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi {
    @GET("getdata")
    suspend fun getUser():LoginResponse

    @POST("logout")
    suspend fun logout():ResponseBody//Responsebody will give all type response
}