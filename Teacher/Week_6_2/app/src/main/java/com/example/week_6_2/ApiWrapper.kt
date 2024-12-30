package com.example.week_6_2

import kotlinx.serialization.Serializable

@Serializable
data class ApiWrapper (
    val success: Boolean,
    val data: MemeWrapper
)