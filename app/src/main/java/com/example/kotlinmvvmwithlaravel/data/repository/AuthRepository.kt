package com.example.kotlinmvvmwithlaravel.data.repository

import com.example.kotlinmvvmwithlaravel.data.UserPreferences
import com.example.kotlinmvvmwithlaravel.data.network.AuthApi

class AuthRepository(
    private val api:AuthApi,
    private val preferences: UserPreferences

) : BaseRepository() {

    suspend fun Login (
        email:String,
        password:String

    ) = safeApiCall {
        api.login(email,password)
    }
    suspend fun saveAuthToken(token:String){
        preferences.saveAuthToken(token)
    }
}