package com.example.kotlinmvvmwithlaravel.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.kotlinmvvmwithlaravel.R
import com.example.kotlinmvvmwithlaravel.data.network.Resource
import com.example.kotlinmvvmwithlaravel.data.network.UserApi
import com.example.kotlinmvvmwithlaravel.data.repository.UserRepository
import com.example.kotlinmvvmwithlaravel.data.responses.User
import com.example.kotlinmvvmwithlaravel.databinding.FragmentHomeBinding
import com.example.kotlinmvvmwithlaravel.ui.base.BaseFragment
import com.example.kotlinmvvmwithlaravel.ui.visible
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking


class HomeFragment : BaseFragment<HomeViewModel,FragmentHomeBinding,UserRepository> (){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressbar.visible(false)

        viewModel.getUser()
        viewModel.user.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Success ->{
                    binding.progressbar.visible(false)

                    updateUI(it.value.user)
                }
                is Resource.Loading->{
                    binding.progressbar.visible(true)

                }
            }
        })

        binding.buttonLogout.setOnClickListener {
            logout()
        }
    }

    private fun updateUI(user: User) {
        with(binding){
            textViewId.text=user.id.toString()
            textViewName.text=user.name
            textViewEmail.text=user.email

        }

    }


    override fun getViewModel()=HomeViewModel::class.java

    override fun getFragmentBinding(
        inflator: LayoutInflater,
        container: ViewGroup?
    )= FragmentHomeBinding.inflate(inflator,container,false)

    override fun getFragmentRepository(): UserRepository {
//        for this project only ,as we don't use runblocking in project ,it may give ANR
        val token = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataSource.buildApi(UserApi::class.java,token)
        return UserRepository(api)
    }


}