package com.example.kotlinmvvmwithlaravel.ui.auth

import androidx.lifecycle.ViewModel
import com.example.kotlinmvvmwithlaravel.repository.AuthRepository

class AuthViewModel(
    private val repository: AuthRepository
):ViewModel() {
}