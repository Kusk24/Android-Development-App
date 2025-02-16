package com.example.listvariation

import kotlinx.serialization.Serializable

@Serializable
data class PersonResultWrapper(
    val results: List<PersonResult> = listOf()
)

@Serializable
data class PersonResult(
    val name: PersonName = PersonName()
)

@Serializable
data class PersonName(
    val title: String = "",
    val first: String = "",
    val last: String = ""
)
