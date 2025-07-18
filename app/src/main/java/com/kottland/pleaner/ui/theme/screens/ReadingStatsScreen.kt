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
import com.kottland.pleaner.ui.features.readingstats.ReadingStatsViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ReadingStatsScreen(
    viewModel: ReadingStatsViewModel = hiltViewModel()
) {
    val readingStats by viewModel.readingStats.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(readingStats) { stats ->
            val date = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date(stats.date))
            Text(
                text = "Date: $date, Pages Read: ${stats.pagesRead}, Time Spent: ${stats.timeSpent}",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
