package com.kottland.pleaner.di

import android.content.Context
import androidx.room.Room
import com.kottland.pleaner.data.local.BookDao
import com.kottland.pleaner.data.local.ChapterDao
import com.kottland.pleaner.data.local.PleanerDatabase
import com.kottland.pleaner.data.local.QuizDao
import com.kottland.pleaner.data.local.ReadingStatsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providePleanerDatabase(@ApplicationContext context: Context): PleanerDatabase {
        return Room.databaseBuilder(
            context,
            PleanerDatabase::class.java,
            "pleaner.db"
        ).build()
    }

    @Provides
    fun provideBookDao(database: PleanerDatabase): BookDao {
        return database.bookDao()
    }

    @Provides
    fun provideChapterDao(database: PleanerDatabase): ChapterDao {
        return database.chapterDao()
    }

    @Provides
    fun provideReadingStatsDao(database: PleanerDatabase): ReadingStatsDao {
        return database.readingStatsDao()
    }

    @Provides
    fun provideQuizDao(database: PleanerDatabase): QuizDao {
        return database.quizDao()
    }
}
