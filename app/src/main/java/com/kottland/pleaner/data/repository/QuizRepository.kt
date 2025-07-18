package com.kottland.pleaner.data.repository

import com.kottland.pleaner.data.local.Question
import com.kottland.pleaner.data.local.Quiz
import com.kottland.pleaner.data.local.QuizDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QuizRepository @Inject constructor(
    private val quizDao: QuizDao
) {
    fun getQuizzesForBook(bookId: Long): Flow<List<Quiz>> {
        return quizDao.getQuizzesForBook(bookId)
    }

    fun getQuestionsForQuiz(quizId: Long): Flow<List<Question>> {
        return quizDao.getQuestionsForQuiz(quizId)
    }

    suspend fun insertQuiz(quiz: Quiz, questions: List<Question>) {
        val quizId = quizDao.insertQuiz(quiz)
        quizDao.insertQuestions(questions.map { it.copy(quizId = quizId) })
    }
}
