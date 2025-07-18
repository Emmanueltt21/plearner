package com.kottland.pleaner.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reading_stats")
data class ReadingStats(
    @PrimaryKey
    val date: Long,
    val pagesRead: Int,
    val timeSpent: Long
)
