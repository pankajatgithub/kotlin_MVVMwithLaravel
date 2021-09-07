package com.example.kotlinmvvmwithlaravel.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlinmvvmwithlaravel.R
import com.example.kotlinmvvmwithlaravel.databinding.FragmentLoginBinding
import com.example.kotlinmvvmwithlaravel.network.AuthApi
import com.example.kotlinmvvmwithlaravel.repository.AuthRepository
import com.example.kotlinmvvmwithlaravel.ui.base.BaseFragment


class LoginFragment : BaseFragment<AuthViewModel,FragmentLoginBinding,AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun getViewModel()=AuthViewModel::class.java

    override fun getFragmentBinding(
        inflator: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflator,container,false)

    override fun getFragmentRepository() = AuthRepository(remoteDataSource.buildApi(AuthApi::class.java))


}