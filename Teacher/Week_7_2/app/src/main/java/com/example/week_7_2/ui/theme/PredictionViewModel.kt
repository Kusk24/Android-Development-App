package com.example.week_7_2.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.week_7_2.PredictionResponse
import com.example.week_7_2.httpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PredictionViewModel : ViewModel() {
    private val _currentName = MutableStateFlow("")
    val currentName: StateFlow<String> = _currentName

    private val _predictionResponse = MutableStateFlow(PredictionResponse())
    val predictionResponse: StateFlow<PredictionResponse> = _predictionResponse

    fun predictName(name: String) {
        val url = "https://api.nationalize.io/?name=$name"
        viewModelScope.launch {
            val respondedPrediction = httpClient.get(url).body<PredictionResponse>()
            _predictionResponse.value = respondedPrediction
        }
    }

    fun updateCurrentName(name: String) {
        _currentName.value = name
    }
}