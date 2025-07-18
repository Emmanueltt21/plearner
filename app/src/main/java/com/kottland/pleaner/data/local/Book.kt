package com.kottland.pleaner.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class Book(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val author: String,
    val filePath: String,
    val coverImagePath: String? = null,
    val currentPage: Int = 0,
    val totalPages: Int,
    val lastReadTimestamp: Long = System.currentTimeMillis()
)
