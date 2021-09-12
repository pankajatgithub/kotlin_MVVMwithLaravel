package com.example.kotlinmvvmwithlaravel.data.repository

import com.example.kotlinmvvmwithlaravel.data.UserPreferences
import com.example.kotlinmvvmwithlaravel.data.network.AuthApi
import retrofit2.http.Field

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

    suspend fun Signup(
        name:String,
       email:String,
       password:String,
    password_confirmation:String
    ) = safeApiCall {
        api.signUp(name,email,password,password_confirmation)
    }
}