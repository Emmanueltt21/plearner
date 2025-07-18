package com.kottland.pleaner.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ReadingStatsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReadingStats(readingStats: ReadingStats)

    @Query("SELECT * FROM reading_stats ORDER BY date DESC")
    fun getAllReadingStats(): Flow<List<ReadingStats>>
}
