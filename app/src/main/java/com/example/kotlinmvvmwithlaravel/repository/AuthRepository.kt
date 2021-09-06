package com.example.kotlinmvvmwithlaravel.repository

import com.example.kotlinmvvmwithlaravel.network.AuthApi

class AuthRepository(
    private val api:AuthApi
) : BaseRepository() {
    suspend fun Login (
        email:String,
        password:String

    ) = safeApiCall {
        api.login(email,password)
    }
}