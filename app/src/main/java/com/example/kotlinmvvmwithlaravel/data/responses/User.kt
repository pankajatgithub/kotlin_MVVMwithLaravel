package com.example.kotlinmvvmwithlaravel.data.responses

data class User(
    val access_token: String?,//making it nullable for getuser api,and change code accordingly for other api use
    val created_at: String,
    val email: String,
    val email_verified_at: Any,
    val id: Int,
    val name: String,
    val updated_at: String
)