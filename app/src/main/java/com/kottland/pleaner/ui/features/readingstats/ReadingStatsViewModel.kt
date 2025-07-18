package com.kottland.pleaner.ui.features.readingstats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kottland.pleaner.data.local.ReadingStats
import com.kottland.pleaner.data.repository.ReadingStatsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ReadingStatsViewModel @Inject constructor(
    private val readingStatsRepository: ReadingStatsRepository
) : ViewModel() {

    val readingStats: StateFlow<List<ReadingStats>> = readingStatsRepository.getAllReadingStats()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
}
