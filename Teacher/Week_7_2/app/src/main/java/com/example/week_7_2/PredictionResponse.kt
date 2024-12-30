package com.example.week_7_2

import kotlinx.serialization.Serializable

@Serializable
data class PredictionResponse (
    val count: Int = 0,
    val name: String = "",
    val country: List<PredictionCountry> = listOf()
)