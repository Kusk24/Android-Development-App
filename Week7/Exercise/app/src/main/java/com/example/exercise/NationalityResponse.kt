package com.example.exercise

data class NationalityResponse(
    var count: Int,
    var name: String,
    var PredictionResponse: List<PredictionResponse>
)

data class PredictionResponse (
    var country_id: String,
    var probablity: Int
)