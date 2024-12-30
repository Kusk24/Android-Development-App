package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MemeViewModel : ViewModel() {
    private val _memes = MutableStateFlow(listOf<Memes>())

    val memes: StateFlow<List<Memes>> = _memes

    init{
        loadMeme()
    }

    fun loadMeme(){
        val url  = "https://api.imgflip.com/get_memes"
        viewModelScope.launch {
            val data = httpClient.get(url).body<ApiWrapper>()
            _memes.value = data.data.memes
        }
    }

}