package com.bibleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bibleapp.ui.Screen
import com.bibleapp.ui.components.BottomNav
import com.bibleapp.ui.screens.*
import com.bibleapp.ui.theme.BibleAppTheme
import com.bibleapp.viewmodel.BibleViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val vm: BibleViewModel = viewModel()
            val settings by vm.settings.collectAsState()
            val navController = rememberNavController()

            BibleAppTheme(darkTheme = settings.theme == "dark") {
                Scaffold(
                    bottomBar = { BottomNav(navController) }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Home.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(Screen.Home.route) {
                            HomeScreen(navController, vm)
                        }
                        composable(Screen.Books.route) {
                            BooksScreen(navController)
                        }
                        composable(Screen.Chapters.route) { backStackEntry ->
                            val bookId = backStackEntry.arguments?.getString("bookId") ?: return@composable
                            ChaptersScreen(bookId, navController)
                        }
                        composable(Screen.Reader.route) { backStackEntry ->
                            val bookId = backStackEntry.arguments?.getString("bookId") ?: return@composable
                            val chapter = backStackEntry.arguments?.getString("chapter")?.toIntOrNull() ?: 1
                            ReaderScreen(bookId, chapter, navController, vm)
                        }
                        composable(Screen.Search.route) {
                            SearchScreen(navController, vm)
                        }
                        composable(Screen.Saved.route) {
                            SavedScreen(navController, vm)
                        }
                        composable(Screen.Settings.route) {
                            SettingsScreen(vm)
                        }
                    }
                }
            }
        }
    }
}
