package com.example.week_7_2

import kotlinx.serialization.Serializable

@Serializable
data class PredictionCountry (
    val country_id: String = "",
    val probability: Double = 0.0
)