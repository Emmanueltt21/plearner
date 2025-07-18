package com.kottland.pleaner.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kottland.pleaner.ui.theme.screens.AddBookScreen
import com.kottland.pleaner.ui.theme.screens.HomeScreen
import com.kottland.pleaner.ui.theme.screens.QuizModeScreen
import com.kottland.pleaner.ui.theme.screens.ReadBookScreen
import com.kottland.pleaner.ui.theme.screens.ReadingStatsScreen

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Screen.ReadBook.route,
        modifier = modifier
    ) {
        composable(Screen.AddBook.route) {
            AddBookScreen()
        }
        composable(Screen.ReadBook.route) {
            ReadBookScreen()
        }
        composable(Screen.ReadingStats.route) {
            ReadingStatsScreen()
        }
        composable(Screen.QuizMode.route) {
            QuizModeScreen()
        }
    }
}
