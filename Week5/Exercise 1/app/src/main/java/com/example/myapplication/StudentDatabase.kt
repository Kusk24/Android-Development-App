package com.example.myapplication

import androidx.room.Database


@Database(entities = [Student:: class], version = 1)
class StudentDatabase {
    abstract fun StudentDao
}