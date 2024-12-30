package com.example.myapplication

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
    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name

    init{
        loadData()
    }

    fun loadData() {
        val url = "https://random.dog/woof.json"
        viewModelScope.launch {
            val respondedData = httpClient.get(url).body<RandomDogResponse>()
            Log.d("API Response", respondedData.url)
            _url.value = respondedData.url

//            _response.value = respondedData
        }
    }

}