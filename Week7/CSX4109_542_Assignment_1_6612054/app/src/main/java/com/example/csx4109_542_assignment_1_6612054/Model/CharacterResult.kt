package com.example.csx4109_542_assignment_1_6612054.Model

import kotlinx.serialization.Serializable

@Serializable
data class CharacterResult (
    val id : Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: Detail,
    val location: Detail,
    val image: String,
    val episode: List<String>,
    val url : String,
    val created: String
)