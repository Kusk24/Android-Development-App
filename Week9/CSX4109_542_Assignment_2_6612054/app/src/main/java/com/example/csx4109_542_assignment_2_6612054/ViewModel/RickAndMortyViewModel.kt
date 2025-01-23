package com.example.csx4109_542_assignment_2_6612054.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.csx4109_542_assignment_2_6612054.Model.ApiWrapper
import com.example.csx4109_542_assignment_2_6612054.Model.CharacterResult
import com.example.csx4109_542_assignment_2_6612054.Model.Info
import com.example.csx4109_542_assignment_2_6612054.httpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RickAndMortyViewModel : ViewModel() {
    private val _currentPage = MutableStateFlow(1)
    val currentPage: StateFlow<Int> = _currentPage

    private val _info = MutableStateFlow(Info(0,0,"",""))
    val info : StateFlow<Info> = _info

    private val _results = MutableStateFlow(listOf<CharacterResult>())
    val results : StateFlow<List<CharacterResult>> = _results

    init{
        loadRickAndMorty()
    }

    private fun minusCurrentPage(){
        _currentPage.value--
    }

    private fun plusCurrentPage(){
        _currentPage.value++
    }

    private fun loadRickAndMorty() {
        val url = "https://rickandmortyapi.com/api/character"
        viewModelScope.launch {
            val data = httpClient.get(url).body<ApiWrapper>()

            _info.value = data.info
            _results.value = data.results

        }
    }

    fun loadPage(url: String, coming: String){
        viewModelScope.launch {
            val data = httpClient.get(url).body<ApiWrapper>()

            _info.value = data.info
            _results.value = data.results

            if (coming == ("Prev")){
                minusCurrentPage()
            } else if (coming == ("Next")) {
                plusCurrentPage()
            }
        }
    }
}