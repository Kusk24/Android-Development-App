package com.example.week_5_2.Reqeust

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RequestDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRequest(request: Request)

    @Query("SELECT * FROM requests ORDER BY id DESC LIMIT 1")
    fun getLatestRequest(): Flow<Request>
}