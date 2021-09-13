package com.example.kotlinmvvmwithlaravel.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinmvvmwithlaravel.data.network.Resource
import com.example.kotlinmvvmwithlaravel.data.repository.AuthRepository
import com.example.kotlinmvvmwithlaravel.data.responses.LoginResponse
import com.example.kotlinmvvmwithlaravel.data.responses.SignupResponse
import com.example.kotlinmvvmwithlaravel.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository
) : BaseViewModel(repository) {

    private val _loginResponse:MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val loginResponse: LiveData<Resource<LoginResponse>>
    get() = _loginResponse

    private val _signupResponse:MutableLiveData<Resource<SignupResponse>> = MutableLiveData()
    val signupResponse:LiveData<Resource<SignupResponse>>
    get() = _signupResponse

    fun login(
  email:String,
  password:String
    ) = viewModelScope.launch {
        _loginResponse.value=Resource.Loading
       _loginResponse.value= repository.Login(email,password)
    }

    fun signup(
        name:String,
        email:String,
        password:String,
        password_confirmation:String
    ) = viewModelScope.launch {
        _loginResponse.value=Resource.Loading
        _signupResponse.value=repository.Signup(name,email,password,password_confirmation)

    }

    suspend fun saveAuthToken(token:String) {
        repository.saveAuthToken(token)

    }

}