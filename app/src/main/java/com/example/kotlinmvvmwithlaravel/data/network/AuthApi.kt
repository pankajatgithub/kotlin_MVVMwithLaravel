package com.example.kotlinmvvmwithlaravel.data.network

import com.example.kotlinmvvmwithlaravel.data.responses.LoginResponse
import com.example.kotlinmvvmwithlaravel.data.responses.SignupResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {

    @FormUrlEncoded
    @POST("auth/login")
    suspend fun login(
        @Field("email")email:String,
        @Field("password")password:String
    ):LoginResponse

    @FormUrlEncoded
    @POST("auth/signup")
    suspend fun signUp(
        @Field("name")name:String,
        @Field("email")email:String,
        @Field("password")password:String,
        @Field("password_confirmation")password_confirmation:String
    ): SignupResponse

}