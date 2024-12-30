package com.example.myapplication

import kotlinx.serialization.Serializable

@Serializable
data class ApiWrapper (
    val success: Boolean,
    val data: MemeWrapper
)

@Serializable
data class MemeWrapper(
    val memes: List<Memes>
)

@Serializable
data class Memes(
    val id: String,
    val name: String,
    val url: String,
    val width: Int,
    val height: Int,
    val box_count: Int,
    val captions: Int
)