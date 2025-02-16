package com.example.week_13_2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _currentPersonNames = mutableListOf<PersonName>()
    private val _personNames = MutableStateFlow(mutableListOf<PersonName>())
    val personNames: StateFlow<List<PersonName>> = _personNames

    fun loadPerson() {
        val url = "https://randomuser.me/api"
        viewModelScope.launch {
            val response = httpClient.get(url).body<PersonResultWrapper>()
            val name = response.results.first().name
            _currentPersonNames.add(name)
            _currentPersonNames.sortBy { it.first }

//            _personNames.value = mutableListOf()
//            _currentPersonNames.forEach { item ->
//                _personNames.value.add(item)
//            }

            _personNames.value = _currentPersonNames.map { it }.toMutableList()
        }
    }

}