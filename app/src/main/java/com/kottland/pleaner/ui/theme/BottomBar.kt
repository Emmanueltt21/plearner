package com.kottland.pleaner.ui.theme

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    object AddBook : Screen("add_book", "Add Book", Icons.Default.Add)
    object ReadBook : Screen("read_book", "Read Book", Icons.Default.Book)
    object ReadingStats : Screen("reading_stats", "Reading Stats", Icons.Default.Analytics)
    object QuizMode : Screen("quiz_mode", "Quiz Mode", Icons.Default.Quiz)
}

val bottomNavItems = listOf(
    Screen.AddBook,
    Screen.ReadBook,
    Screen.ReadingStats,
    Screen.QuizMode
)

@Composable
fun PleanerBottomBar(navController: NavController) {
    NavigationBar {
        bottomNavItems.forEach { screen ->
            NavigationBarItem(
                icon = { Icon(screen.icon, contentDescription = screen.label) },
                label = { Text(screen.label) },
                selected = false,
                onClick = { navController.navigate(screen.route) }
            )
        }
    }
}
