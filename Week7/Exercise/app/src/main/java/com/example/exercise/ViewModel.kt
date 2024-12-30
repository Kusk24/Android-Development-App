package com.example.exercise

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel: ViewModel() {
    private val _currentName = MutableStateFlow("")
    val currentName: StateFlow<String> = _currentName

    fun updateCurrentName(name: String){
        _currentName.value = name
    }
}