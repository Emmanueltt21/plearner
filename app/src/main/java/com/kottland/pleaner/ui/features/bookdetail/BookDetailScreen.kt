package com.kottland.pleaner.ui.features.bookdetail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
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

@Composable
fun BookDetailScreen(
    viewModel: BookDetailViewModel = hiltViewModel(),
    onChapterClick: (Long) -> Unit
) {
    val book by viewModel.book.collectAsState()
    val chapters by viewModel.chapters.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        book?.let {
            Text(text = it.title)
        }
        LazyColumn {
            items(chapters) { chapter ->
                Text(
                    text = chapter.title,
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable {
                            onChapterClick(chapter.id)
                        }
                )
            }
        }
    }
}
