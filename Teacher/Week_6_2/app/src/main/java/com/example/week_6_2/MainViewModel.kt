package com.example.week_6_2

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _url = MutableStateFlow("")
    val url: StateFlow<String> = _url

    init {
        loadData()
    }

    fun loadData() {
        val url = "https://random.dog/woof.json"

        viewModelScope.launch {
            val respondedData = httpClient.get(url).body<RandomDogResponse>()
            _url.value = respondedData.url
        }
    }

}