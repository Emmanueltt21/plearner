package com.kottland.pleaner.data.repository

import com.kottland.pleaner.data.local.ReadingStats
import com.kottland.pleaner.data.local.ReadingStatsDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadingStatsRepository @Inject constructor(
    private val readingStatsDao: ReadingStatsDao
) {
    fun getAllReadingStats(): Flow<List<ReadingStats>> {
        return readingStatsDao.getAllReadingStats()
    }

    suspend fun insertReadingStats(readingStats: ReadingStats) {
        readingStatsDao.insertReadingStats(readingStats)
    }
}
