package com.example.kotlinmvvmwithlaravel.data.repository

import com.example.kotlinmvvmwithlaravel.data.UserPreferences
import com.example.kotlinmvvmwithlaravel.data.network.AuthApi
import com.example.kotlinmvvmwithlaravel.data.network.UserApi

class UserRepository(
    private val api:UserApi,

) : BaseRepository() {

   suspend fun getUser()=safeApiCall {
       api.getUser()

   }

}