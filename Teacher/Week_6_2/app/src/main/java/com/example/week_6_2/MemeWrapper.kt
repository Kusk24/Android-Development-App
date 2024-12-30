package com.example.week_6_2

import kotlinx.serialization.Serializable

@Serializable
data class MemeWrapper (
    val memes: List<Meme>
)