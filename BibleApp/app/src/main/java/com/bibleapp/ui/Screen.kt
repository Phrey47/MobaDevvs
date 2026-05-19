package com.bibleapp.ui

sealed class Screen(val route: String) {
    object Home     : Screen("home")
    object Books    : Screen("books")
    object Chapters : Screen("chapters/{bookId}") {
        fun createRoute(bookId: String) = "chapters/$bookId"
    }
    object Reader   : Screen("reader/{bookId}/{chapter}") {
        fun createRoute(bookId: String, chapter: Int) = "reader/$bookId/$chapter"
    }
    object Search   : Screen("search")
    object Bookmarks : Screen("bookmarks")
    object Settings : Screen("settings")
}
