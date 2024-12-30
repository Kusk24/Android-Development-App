package com.example.classexercise

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

// Extension to create DataStore instance
val Application.dataStore: DataStore<Preferences> by preferencesDataStore("employee_data")

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val dataStore: DataStore<Preferences> = application.dataStore
    private val NAME_KEY = stringPreferencesKey("name")
    private val AGE_KEY = stringPreferencesKey("age")
    private val ISSUE_KEY = stringPreferencesKey("issue")

    // Flow to observe the latest saved information
    val latestInfo: StateFlow<String?> = dataStore.data
        .catch { exception ->
            // Handle exceptions, such as IOException
            emit(emptyPreferences())
        }
        .map { prefs ->
            val name = prefs[NAME_KEY] ?: ""
            val age = prefs[AGE_KEY] ?: ""
            val issue = prefs[ISSUE_KEY] ?: ""
            if (name.isNotEmpty() && age.isNotEmpty() && issue.isNotEmpty()) {
                "Name: $name, \nAge: $age, \nIssue: $issue"
            } else {
                null
            }
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, null)

    // Function to save form data
    fun submitForm(name: String, age: String, issue: String) {
        viewModelScope.launch {
            dataStore.edit { prefs ->
                prefs[NAME_KEY] = name
                prefs[AGE_KEY] = age
                prefs[ISSUE_KEY] = issue
            }
        }
    }
}
