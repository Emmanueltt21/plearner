package com.kottland.pleaner.ui.theme.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kottland.pleaner.ui.features.quiz.QuizViewModel

@Composable
fun QuizModeScreen(
    viewModel: QuizViewModel = hiltViewModel()
) {
    val quizzes by viewModel.quizzes.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(quizzes) { quiz ->
            Text(
                text = quiz.title,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
