package com.example.kotlinmvvmwithlaravel.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.kotlinmvvmwithlaravel.network.RemoteDataSource
import com.example.kotlinmvvmwithlaravel.repository.BaseRepository

abstract class BaseFragment<VM:ViewModel,B:ViewBinding,R:BaseRepository>:Fragment() {

    protected lateinit var binding:B
    protected lateinit var viewModel: VM
    protected val remoteDataSource = RemoteDataSource()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=getFragmentBinding(inflater,container)


        val factory=ViewModelFactory(getFragmentRepository())
        viewModel=ViewModelProvider(this,factory).get(getViewModel())
        return binding.root

//        return super.onCreateView(inflater, container, savedInstanceState)
    }
    abstract fun getViewModel():Class<VM>
    abstract fun getFragmentBinding(inflator:LayoutInflater,container: ViewGroup?):B
    abstract fun getFragmentRepository():R
}