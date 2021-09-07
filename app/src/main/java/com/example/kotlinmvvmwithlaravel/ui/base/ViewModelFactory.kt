package com.example.kotlinmvvmwithlaravel.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinmvvmwithlaravel.repository.AuthRepository
import com.example.kotlinmvvmwithlaravel.repository.BaseRepository
import com.example.kotlinmvvmwithlaravel.ui.auth.AuthViewModel

//https://www.youtube.com/watch?v=aF2wuJqe2KM&list=PLk7v1Z2rk4hgmIvyw8rvpiEQxIAbJvDAF&index=7
class ViewModelFactory(
    private val repository: BaseRepository
) :ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

      return  when{
            modelClass.isAssignableFrom(AuthViewModel::class.java)->AuthViewModel(repository as AuthRepository) as T
       else ->throw IllegalArgumentException("ViewModelClass not found")
        }
    }
}