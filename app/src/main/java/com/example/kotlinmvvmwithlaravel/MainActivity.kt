package com.example.kotlinmvvmwithlaravel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.example.kotlinmvvmwithlaravel.data.UserPreferences
import com.example.kotlinmvvmwithlaravel.ui.auth.AuthActivity
import com.example.kotlinmvvmwithlaravel.ui.home.HomeActivity
import com.example.kotlinmvvmwithlaravel.ui.startNewActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userPreferences = UserPreferences(this)
        userPreferences.authToken.asLiveData().observe(this, Observer {

            val activity=if(it == null)AuthActivity::class.java else HomeActivity::class.java
            startNewActivity(activity)

            Toast.makeText(this, it?:"Token is null", Toast.LENGTH_SHORT).show()
//            finish()

        })


    }
}