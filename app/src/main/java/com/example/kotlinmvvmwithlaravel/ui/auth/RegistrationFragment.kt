package com.example.kotlinmvvmwithlaravel.ui.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.kotlinmvvmwithlaravel.MainActivity
import com.example.kotlinmvvmwithlaravel.R
import com.example.kotlinmvvmwithlaravel.data.network.AuthApi
import com.example.kotlinmvvmwithlaravel.data.network.Resource
import com.example.kotlinmvvmwithlaravel.data.repository.AuthRepository
import com.example.kotlinmvvmwithlaravel.databinding.FragmentRegisterBinding
import com.example.kotlinmvvmwithlaravel.ui.base.BaseFragment
import com.example.kotlinmvvmwithlaravel.ui.home.HomeActivity
import com.example.kotlinmvvmwithlaravel.ui.startNewActivity
import com.example.kotlinmvvmwithlaravel.ui.visible
import kotlinx.coroutines.launch


class RegistrationFragment : BaseFragment<AuthViewModel,FragmentRegisterBinding,AuthRepository>() {


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.progressbar.visible(false)  //extension function call

        binding.buttonRegister.setOnClickListener {
            signup()
        }
        viewModel.signupResponse.observe(viewLifecycleOwner, Observer {
            binding.progressbar.visible(it is Resource.Loading)
            when(it){
                is Resource.Success->{
                    lifecycleScope.launch {
                        requireActivity().startNewActivity(MainActivity::class.java)
                    }

                }
                is Resource.Failure->{
                    Log.e("signupFailure", "onActivityCreated: " )
                }
            }

        })
    }

    private fun signup() {
        val name = binding.editTextTextPersonName.text.toString().trim()
        val email = binding.editTextTextEmailAddress.text.toString().trim()
        val password = binding.editTextTextPassword.text.toString().trim()
        val confirm_password = binding.editTextTextConfirmPassword.text.toString().trim()
        viewModel.signup(name,email,password,confirm_password)

    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflator: LayoutInflater,
        container: ViewGroup?
    ) = FragmentRegisterBinding.inflate(inflator,container,false)

    override fun getFragmentRepository() =
        AuthRepository(remoteDataSource.buildApi(AuthApi::class.java),userPreferences)

}