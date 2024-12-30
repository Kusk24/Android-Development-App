package com.example.week_6_2

import kotlinx.serialization.Serializable

@Serializable
data class Meme (
    val name: String,
    val url: String
)