package com.example.week_5_2

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.week_5_2.Reqeust.Request
import com.example.week_5_2.Reqeust.RequestDAO

@Database(entities = [Student::class, Request::class], version = 2)
abstract class StudentDatabase : RoomDatabase(){
    abstract fun StudentDAO(): StudentDAO
    abstract fun RequestDAO(): RequestDAO
}