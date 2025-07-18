package com.kottland.pleaner.ui.theme.screens

import androidx.compose.foundation.clickable
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
import com.kottland.pleaner.ui.features.booklist.BookListViewModel

@Composable
fun ReadBookScreen(
    viewModel: BookListViewModel = hiltViewModel(),
    onBookClick: (Long) -> Unit
) {
    val books by viewModel.books.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(books) { book ->
            Text(
                text = book.title,
                modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                        onBookClick(book.id)
                    }
            )
        }
    }
}
