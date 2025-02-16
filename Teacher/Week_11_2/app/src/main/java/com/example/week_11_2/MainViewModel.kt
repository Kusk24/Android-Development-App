package com.example.week_11_2

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

    private val _country = MutableStateFlow("")
    val country: StateFlow<String> = _country

    private val _similarUniversities = MutableStateFlow(listOf<University>())
    val similarUniversities: StateFlow<List<University>> = _similarUniversities

    fun updateKeyword(currentKeyword: String) {
        _keyword.value = currentKeyword
    }

    fun searchByKeyword(keyword: String) {
        val url = "http://universities.hipolabs.com/search?name=$keyword"
        viewModelScope.launch {
            val response = httpClient.get(url).body<List<University>>()
            _universities.value = response

            if (response.isNotEmpty()) {
                _country.value = response[0].country
                searchByCountry(_country.value)
            }
        }
    }

    fun searchByCountry(country: String) {
        val url = "http://universities.hipolabs.com/search?country=$country"
        viewModelScope.launch {
            val response = httpClient.get(url).body<List<University>>()
            _similarUniversities.value = response
            _country.value = country
        }
    }
}