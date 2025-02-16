package com.example.week_11_2

import kotlinx.serialization.Serializable

@Serializable
data class University (
    val name: String = "",
    val country: String = ""
)