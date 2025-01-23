package com.example.week_5_2.Reqeust

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "requests")
data class Request (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val age: Int,
    val detail: String
)