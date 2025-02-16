package com.example.week10_2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _keyword = MutableStateFlow("")
    val keyword: StateFlow<String> = _keyword

    private val _universities = MutableStateFlow(listOf<University>())
    val universities: StateFlow<List<University>> = _universities

    fun updateKeyword(updatedKeyword: String) {
        _keyword.value = updatedKeyword
    }

    fun search(keyword: String) {
        val url = "http://universities.hipolabs.com/search?name=$keyword"
        viewModelScope.launch {
            val response = httpClient.get(url).body<List<University>>()
            _universities.value = response
        }
    }
}