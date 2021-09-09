package com.example.kotlinmvvmwithlaravel.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.kotlinmvvmwithlaravel.databinding.FragmentLoginBinding
import com.example.kotlinmvvmwithlaravel.data.network.AuthApi
import com.example.kotlinmvvmwithlaravel.data.network.Resource
import com.example.kotlinmvvmwithlaravel.data.repository.AuthRepository
import com.example.kotlinmvvmwithlaravel.ui.base.BaseFragment
import com.example.kotlinmvvmwithlaravel.ui.enable
import com.example.kotlinmvvmwithlaravel.ui.handleApiErrors
import com.example.kotlinmvvmwithlaravel.ui.home.HomeActivity
import com.example.kotlinmvvmwithlaravel.ui.startNewActivity
import com.example.kotlinmvvmwithlaravel.ui.visible
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.coroutines.launch


class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.progressbar.visible(false)  //extension function call
        binding.buttonLogin.enable(false)


        binding.editTextTextPassword.addTextChangedListener {
            val email=binding.editTextTextEmailAddress.text.toString().trim()
            binding.buttonLogin.enable(email.isNotEmpty() && it.toString().isNotEmpty())
        }

            viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            binding.progressbar.visible(it is Resource.Loading)  //extension function call

            when (it) {
                is Resource.Success -> {
                    lifecycleScope.launch {
                        viewModel.saveAuthToken(it.value.user.access_token!!)//we can't use !! operator as it may give null pointer exception
                        requireActivity().startNewActivity(HomeActivity::class.java) //extension function call
                    }
                    Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_LONG).show()
                }
                is Resource.Failure -> handleApiErrors(it){ login() }//for retry Login
            }
        })

        binding.buttonLogin.setOnClickListener {
            login()
//            binding.progressbar.visible(true)  //extension function call

        }

    }

    private fun login() {
        val email = binding.editTextTextEmailAddress.text.toString().trim()
        val password = binding.editTextTextPassword.text.toString().trim()
        viewModel.login(email, password)
    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflator: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflator, container, false)

    override fun getFragmentRepository() =
        AuthRepository(remoteDataSource.buildApi(AuthApi::class.java),userPreferences)


}