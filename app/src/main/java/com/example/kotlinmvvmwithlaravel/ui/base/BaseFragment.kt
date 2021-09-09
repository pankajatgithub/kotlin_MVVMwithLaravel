package com.example.kotlinmvvmwithlaravel.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.example.kotlinmvvmwithlaravel.data.UserPreferences
import com.example.kotlinmvvmwithlaravel.data.network.RemoteDataSource
import com.example.kotlinmvvmwithlaravel.data.network.UserApi
import com.example.kotlinmvvmwithlaravel.data.repository.BaseRepository
import com.example.kotlinmvvmwithlaravel.ui.auth.AuthActivity
import com.example.kotlinmvvmwithlaravel.ui.startNewActivity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

abstract class BaseFragment<VM:BaseViewModel,B:ViewBinding,R:BaseRepository>:Fragment() {

    protected lateinit var userPreferences: UserPreferences
    protected lateinit var binding:B
    protected lateinit var viewModel: VM
    protected val remoteDataSource = RemoteDataSource()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        userPreferences= UserPreferences(requireContext())
        binding=getFragmentBinding(inflater,container)
        val factory=ViewModelFactory(getFragmentRepository())
        viewModel=ViewModelProvider(this,factory).get(getViewModel())

        //        for this project only ,as we don't use runblocking in project ,it may give ANR
         lifecycleScope.launch { userPreferences.authToken.first() }
        return binding.root

//        return super.onCreateView(inflater, container, savedInstanceState)
    }
    abstract fun getViewModel():Class<VM>
    abstract fun getFragmentBinding(inflator:LayoutInflater,container: ViewGroup?):B
    abstract fun getFragmentRepository():R

    fun logout()=lifecycleScope.launch {
     val authToken=userPreferences.authToken.first()
        val api = remoteDataSource.buildApi(UserApi::class.java,authToken)
        viewModel.logout(api)
        userPreferences.clear()
        requireActivity().startNewActivity(AuthActivity::class.java)
    }
}