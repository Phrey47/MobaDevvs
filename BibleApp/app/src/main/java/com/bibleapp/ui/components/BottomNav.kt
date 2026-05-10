package com.bibleapp.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.bibleapp.ui.Screen

private data class NavItem(val label: String, val icon: ImageVector, val route: String)

private val NAV_ITEMS = listOf(
    NavItem("Home",     Icons.Outlined.Home,        Screen.Home.route),
    NavItem("Bible",    Icons.Outlined.MenuBook,     Screen.Books.route),
    NavItem("Search",   Icons.Outlined.Search,       Screen.Search.route),
    NavItem("Saved",    Icons.Outlined.Bookmark,     Screen.Saved.route),
    NavItem("Settings", Icons.Outlined.Settings,     Screen.Settings.route),
)

@Composable
fun BottomNav(navController: NavController) {
    val backStack = navController.currentBackStackEntryAsState()
    val currentRoute = backStack.value?.destination?.route

    NavigationBar {
        NAV_ITEMS.forEach { item ->
            val selected = currentRoute == item.route ||
                (item.route == Screen.Books.route && currentRoute?.startsWith("chapters") == true)
            NavigationBarItem(
                selected = selected,
                onClick = {
                    if (!selected) {
                        navController.navigate(item.route) {
                            popUpTo(Screen.Home.route) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label, style = MaterialTheme.typography.labelSmall) }
            )
        }
    }
}
