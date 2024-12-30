package com.example.myapplication

import kotlinx.serialization.Serializable


@Serializable
data class RandomDogResponse (
    val url: String
)