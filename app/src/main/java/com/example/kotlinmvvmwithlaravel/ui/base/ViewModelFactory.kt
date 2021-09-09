package com.example.kotlinmvvmwithlaravel.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinmvvmwithlaravel.data.repository.AuthRepository
import com.example.kotlinmvvmwithlaravel.data.repository.BaseRepository
import com.example.kotlinmvvmwithlaravel.data.repository.UserRepository
import com.example.kotlinmvvmwithlaravel.ui.auth.AuthViewModel
import com.example.kotlinmvvmwithlaravel.ui.home.HomeViewModel

//https://www.youtube.com/watch?v=aF2wuJqe2KM&list=PLk7v1Z2rk4hgmIvyw8rvpiEQxIAbJvDAF&index=7
class ViewModelFactory(
    private val repository: BaseRepository
) :ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

      return  when{
            modelClass.isAssignableFrom(AuthViewModel::class.java)->AuthViewModel(repository as AuthRepository) as T
          modelClass.isAssignableFrom(HomeViewModel::class.java)->HomeViewModel(repository as UserRepository) as T
       else ->throw IllegalArgumentException("ViewModelClass not found")
        }
    }
}