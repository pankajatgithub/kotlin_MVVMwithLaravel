package com.example.kotlinmvvmwithlaravel.data

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class UserPreferences(
    context: Context
) {
    private val applicationContext = context.applicationContext
    private val datastore : DataStore<Preferences>

    init {
        datastore=applicationContext.createDataStore(
            name="My DataStore"
        )
    }

    val authToken:Flow<String?>
    get() = datastore.data.map { preferences ->
        preferences[KEY_AUTH]
    }

    suspend fun saveAuthToken(authToken:String){
        datastore.edit { preferences ->
            preferences[KEY_AUTH]=authToken

        }
    }



    companion object{
        private val KEY_AUTH= preferencesKey<String>("key_auth")
    }
}