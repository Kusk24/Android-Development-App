package com.example.csx4109_542_assignment_2_6612054.Model

import kotlinx.serialization.Serializable

@Serializable
data class Info (
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)