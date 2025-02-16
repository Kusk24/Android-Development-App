package com.example.week10_2

import kotlinx.serialization.Serializable

@Serializable
data class University (
    val name: String = "",
    val country: String = "",
    val web_pages: List<String> = listOf()
)