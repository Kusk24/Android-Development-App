package com.example.csx4109_542_assignment_2_6612054.Model

import kotlinx.serialization.Serializable


@Serializable
data class ApiWrapper (
    val info: Info,
    val results: List<CharacterResult>
)