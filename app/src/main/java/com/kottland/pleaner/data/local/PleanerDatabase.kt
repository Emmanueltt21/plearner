package com.kottland.pleaner.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [Book::class, Chapter::class, ReadingStats::class, Quiz::class, Question::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class PleanerDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
    abstract fun chapterDao(): ChapterDao
    abstract fun readingStatsDao(): ReadingStatsDao
    abstract fun quizDao(): QuizDao
}
