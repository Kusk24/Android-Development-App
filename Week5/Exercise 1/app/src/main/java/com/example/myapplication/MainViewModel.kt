package com.example.myapplication

import android.app.Application
import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import androidx.datastore.preferences.core.edit


//Exercise 1
//class MainViewModel : ViewModel() {
//    val counter = mutableStateOf(0)
//
//    fun add() {
//        counter.value += 1
//    }
//}

//Exercise 2
//class MainViewModel: ViewModel() {
//    private val _studentCode = MutableStateFlow("")
//
//    val studentCode: StateFlow<String> = _studentCode
//    fun updateStudentCode(currentText: String) {
//        _studentCode.value = currentText
//    }
//}

//Exercise 3
//val Context.datastore: DataStore<Preferences> by preferencesDataStore("settings")
//
//class MainViewModel(application: Application): ViewModel(){
//    private val STUDENT_CODE_KEY = stringPreferencesKey("STUDENT_CODE_KEY")
//    val savedStudentCode = application.datastore.data.map { settings ->
//        settings[STUDENT_CODE_KEY] ?: ""
//    }
//
//    private val _studentCode = MutableStateFlow("")
//
//    init {
//        viewModelScope.launch {
//            _studentCode.value = savedStudentCode.first()
//        }
//    }
//
//    val studentCode: StateFlow<String> = _studentCode
//    fun updateStudentCode(currentText : String) {
//        _studentCode.value = currentText
//
//        viewModelScope.launch {
//            context.datastore.edit { settings ->
//                settings[STUDENT_CODE_KEY] = currentText
//            }
//        }
//    }
//}