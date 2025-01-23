package com.example.week_5_2

import android.app.Application
import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.week_5_2.Reqeust.Request
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

val Context.datastore: DataStore<Preferences> by preferencesDataStore("settings")

class MainViewModel(application: Application): AndroidViewModel(application) {

    // Exercise 1
    private val context = application
    private val database = Room.databaseBuilder(context,
        StudentDatabase::class.java,
        "student_database").fallbackToDestructiveMigration()
        .build()

    private val requestDAO = database.RequestDAO()
    private val _name = MutableStateFlow("")
    private val _age = MutableStateFlow("")
    private val _detail = MutableStateFlow("")

    val name: StateFlow<String> = _name
    val age: StateFlow<String> = _age
    val detail: StateFlow<String> = _detail


    private val _latestName = MutableStateFlow("")
    private val _latestAge = MutableStateFlow("")
    private val _latestDetail = MutableStateFlow("")

    val latestName: StateFlow<String> = _latestName
    val latestAge: StateFlow<String> = _latestAge
    val latestDetail: StateFlow<String> = _latestDetail

    init {
        viewModelScope.launch {
            requestDAO.getLatestRequest().collect { request ->
                _latestName.value = request.name
                _latestAge.value = request.age.toString()
                _latestDetail.value = request.detail
            }
        }
    }

    fun setName(name: String) {
        _name.value = name
    }

    fun setAge(age: String) {
        _age.value = age
    }

    fun setDetail(detail: String) {
        _detail.value = detail
    }

    fun saveRequest() {
        val request = Request(
            name = _name.value,
            age = _age.value.trim().toInt(),
            detail = _detail.value)

        viewModelScope.launch {
            requestDAO.insertRequest(request)
        }
    }

    // For Student Code Greeting Example
//    private val studentDAO = database.StudentDAO()
//
//    private val STUDENT_CODE_KEY = stringPreferencesKey("STUDENT_CODE_KEY")
//    val savedStudentCode = context.datastore.data.map { settings ->
//        settings[STUDENT_CODE_KEY] ?: ""
//    }
//
//    private val _studentCode = MutableStateFlow("")
//    init {
////        viewModelScope.launch {
////            _studentCode.value = savedStudentCode.first()
////        }
//
//        viewModelScope.launch {
//            studentDAO.getLatestStudent().collect { student ->
//                student?.let {
//                    _studentCode.value = it.code
//                }
//            }
//        }
//    }
//
//    val studentCode: StateFlow<String> = _studentCode
//    fun updateStudentCode(currentText: String) {
//        _studentCode.value = currentText
//
//        viewModelScope.launch {
//            context.datastore.edit { settings ->
//                settings[STUDENT_CODE_KEY] = currentText
//            }
//        }
//    }
//
//    fun saveStudent() {
//        val student = Student(code = _studentCode.value)
//        viewModelScope.launch {
//            studentDAO.insertStudent(student)
//        }
//    }
}