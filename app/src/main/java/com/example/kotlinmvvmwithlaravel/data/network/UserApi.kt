package com.example.kotlinmvvmwithlaravel.data.network

import com.example.kotlinmvvmwithlaravel.data.responses.LoginResponse
import retrofit2.http.GET

interface UserApi {
    @GET("getdata")
    suspend fun getUser():LoginResponse
}