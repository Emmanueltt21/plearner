package com.kottland.pleaner.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kottland.pleaner.ui.features.bookdetail.BookDetailScreen
import com.kottland.pleaner.ui.features.chapter.ChapterScreen
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
            ReadBookScreen(
                onBookClick = { bookId ->
                    navController.navigate("book_detail/$bookId")
                }
            )
        }
        composable(Screen.ReadingStats.route) {
            ReadingStatsScreen()
        }
        composable(Screen.QuizMode.route) {
            QuizModeScreen()
        }
        composable(
            route = "book_detail/{bookId}",
            arguments = listOf(navArgument("bookId") { type = NavType.LongType })
        ) {
            BookDetailScreen(
                onChapterClick = { chapterId ->
                    navController.navigate("chapter/$chapterId")
                }
            )
        }
        composable(
            route = "chapter/{chapterId}",
            arguments = listOf(navArgument("chapterId") { type = NavType.LongType })
        ) {
            ChapterScreen()
        }
    }
}
