package com.example.kotlinmvvmwithlaravel.data.repository

import com.example.kotlinmvvmwithlaravel.data.network.AuthApi

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