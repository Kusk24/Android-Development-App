package com.example.week_5_2

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
data class Student (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val code: String
)