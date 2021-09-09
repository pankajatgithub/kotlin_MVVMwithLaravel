package com.example.kotlinmvvmwithlaravel.ui

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.fragment.app.Fragment
import com.example.kotlinmvvmwithlaravel.data.network.Resource
import com.example.kotlinmvvmwithlaravel.ui.auth.LoginFragment
import com.google.android.material.snackbar.Snackbar

//select kotlin file
//Kotlin Extension function
fun <A:Activity> Activity.startNewActivity(activity: Class<A>){
    Intent(this,activity).also {
        it.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }}

    //Kotlin Extension function
    fun View.visible(isVisible:Boolean){
        visibility=if(isVisible) View.VISIBLE else View.GONE
    }
    //Kotlin Extension function
    fun View.enable(enabled:Boolean){
        isEnabled=enabled
        alpha=if(enabled) 1f else 0.5f
    }
fun View.snackbar(message:String,action:(()->Unit)?=null){
    val snackbar=Snackbar.make(this,message,Snackbar.LENGTH_LONG)
    action?.let {
        snackbar.setAction("Retry"){
            it()
        }

    }
    snackbar.show()
}

fun Fragment.handleApiErrors(
    failure: Resource.Failure,
    retry:(()->Unit)?=null
){
    when{
         failure.isNetworkError->requireView().snackbar("Please check your internt Connection",retry)
         failure.errorCode==401->{
             if(this is LoginFragment) {
                 requireView().snackbar("You've entered incorrect email or password")
             }  else
             {
                 //@TODO perform logout operation

             }
         }
        else->{
            val error = failure.errorBody?.string().toString()
            requireView().snackbar(error)
        }

    }

}
