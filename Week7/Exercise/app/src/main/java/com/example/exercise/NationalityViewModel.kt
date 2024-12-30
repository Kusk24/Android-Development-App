package com.example.exercise

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NationalityViewModel: ViewModel() {
    private val _currentName = MutableStateFlow("")
    private val _predictionResponse = MutableStateFlow(listOf<PredictionResponse>())

    var PredictionResponse: StateFlow<List<PredictionResponse>> = _predictionResponse
    var currentName: StateFlow<String> = _currentName


    init{
        loadNationality()
    }

    fun loadNationality(){
        val url  = "https://api.nationalize.io/?name=john"
        viewModelScope.launch {
            val data = httpClient.get(url).body<NationalityResponse>()
            _predictionResponse.value = data.PredictionResponse
            _currentName.value = data.name
        }
    }
}

