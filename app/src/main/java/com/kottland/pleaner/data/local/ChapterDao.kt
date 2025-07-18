package com.kottland.pleaner.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ChapterDao {
    @Insert
    suspend fun insertChapters(chapters: List<Chapter>)

    @Query("SELECT * FROM chapters WHERE bookId = :bookId ORDER BY startPage ASC")
    fun getChaptersForBook(bookId: Long): Flow<List<Chapter>>

    @Query("SELECT * FROM chapters WHERE id = :chapterId")
    fun getChapterById(chapterId: Long): Flow<Chapter>
}
