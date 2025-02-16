package com.example.listvariation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ResponseViewModel : ViewModel() {

    //    var person : StateFlow<List<Person>> = _person

    private val _currentPersonNames= mutableListOf<PersonName>()
    private val _personNames = MutableStateFlow(mutableListOf<PersonName>())
    var personNames: StateFlow<List<PersonName>> = _personNames

    init{
        loadResults()
    }

    fun loadResults() {
        val url = "https://randomuser.me/api"
        viewModelScope.launch {
            val data = httpClient.get(url).body<PersonResultWrapper>()
            val name = data.results.first().name
            _currentPersonNames.add(name)

            _personNames.value = mutableListOf()
            _currentPersonNames.forEach{ item ->

                _personNames.value.add(item)

            }
        }
    }
}