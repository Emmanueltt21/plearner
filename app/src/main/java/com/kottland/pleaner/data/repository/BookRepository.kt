package com.kottland.pleaner.data.repository

import com.kottland.pleaner.data.local.Book
import com.kottland.pleaner.data.local.BookDao
import com.kottland.pleaner.data.local.Chapter
import com.kottland.pleaner.data.local.ChapterDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookRepository @Inject constructor(
    private val bookDao: BookDao,
    private val chapterDao: ChapterDao
) {
    fun getAllBooks(): Flow<List<Book>> {
        return bookDao.getAllBooks()
    }

    fun getBookById(bookId: Long): Flow<Book> {
        return bookDao.getBookById(bookId)
    }

    fun getChaptersForBook(bookId: Long): Flow<List<Chapter>> {
        return chapterDao.getChaptersForBook(bookId)
    }

    fun getChapterById(chapterId: Long): Flow<Chapter> {
        return chapterDao.getChapterById(chapterId)
    }

    suspend fun insertBook(book: Book, chapters: List<Chapter>) {
        val bookId = bookDao.insertBook(book)
        chapterDao.insertChapters(chapters.map { it.copy(bookId = bookId) })
    }

    suspend fun updateBook(book: Book) {
        bookDao.updateBook(book)
    }

    suspend fun deleteBook(book: Book) {
        bookDao.deleteBook(book)
    }
}
