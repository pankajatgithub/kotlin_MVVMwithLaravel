package com.example.kotlinmvvmwithlaravel.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinmvvmwithlaravel.network.Resource
import com.example.kotlinmvvmwithlaravel.repository.AuthRepository
import com.example.kotlinmvvmwithlaravel.responses.LoginResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class AuthViewModel(
    private val repository: AuthRepository
) : ViewModel() {

    private val _loginResponse:MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val loginResponse: LiveData<Resource<LoginResponse>>
    get() = _loginResponse

    fun login(
  email:String,
  password:String
    ) = viewModelScope.launch {
       _loginResponse.value= repository.Login(email,password)
    }

}