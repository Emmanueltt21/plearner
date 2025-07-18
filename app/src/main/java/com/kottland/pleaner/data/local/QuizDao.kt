package com.kottland.pleaner.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface QuizDao {
    @Insert
    suspend fun insertQuiz(quiz: Quiz): Long

    @Insert
    suspend fun insertQuestions(questions: List<Question>)

    @Query("SELECT * FROM quizzes WHERE bookId = :bookId")
    fun getQuizzesForBook(bookId: Long): Flow<List<Quiz>>

    @Query("SELECT * FROM questions WHERE quizId = :quizId")
    fun getQuestionsForQuiz(quizId: Long): Flow<List<Question>>
}
