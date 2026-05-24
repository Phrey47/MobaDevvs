package com.bibleapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bibleapp.data.*
import com.bibleapp.ui.Screen
import com.bibleapp.ui.theme.Amber600

@Composable
fun BooksScreen(navController: NavController) {
    var tab by remember { mutableStateOf("OT") }
    var query by remember { mutableStateOf("") }

    val books = if (tab == "OT") OT_BOOKS else NT_BOOKS
    val filtered = if (query.isBlank()) books
    else books.filter { it.name.contains(query, ignoreCase = true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Header
        Surface(color = MaterialTheme.colorScheme.surface) {
            Column(Modifier.padding(start = 20.dp, end = 20.dp, top = 52.dp, bottom = 12.dp)) {
                Text("Books", fontSize = 24.sp, fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(bottom = 12.dp))
                // Search field
                OutlinedTextField(
                    value = query,
                    onValueChange = { query = it },
                    placeholder = { Text("Search books…", fontSize = 14.sp) },
                    leadingIcon = { Icon(Icons.Outlined.Search, null, modifier = Modifier.size(18.dp)) },
                    trailingIcon = if (query.isNotEmpty()) {
                        { IconButton(onClick = { query = "" }) {
                            Icon(Icons.Outlined.Clear, null, modifier = Modifier.size(16.dp)) } }
                    } else null,
                    singleLine = true,
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Amber600,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outline
                    )
                )
                Spacer(Modifier.height(10.dp))
                // Tabs
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    listOf("OT" to "Old Testament", "NT" to "New Testament").forEach { (key, label) ->
                        Button(
                            onClick = { tab = key },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp),
                            colors = if (tab == key)
                                ButtonDefaults.buttonColors(containerColor = Amber600)
                            else
                                ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                    contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                        ) { Text(label, fontSize = 13.sp) }
                    }
                }
            }
        }
        HorizontalDivider()

        // Book grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item(span = { GridItemSpan(2) }) {
                Text("${filtered.size} books", fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant, fontWeight = FontWeight.Medium)
            }
            items(filtered) { book ->
                BookCard(book = book,
                    onClick = { navController.navigate(Screen.Chapters.createRoute(book.id)) })
            }
        }
    }
}

@Composable
private fun BookCard(book: BibleBook, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(0.dp),
        border = CardDefaults.outlinedCardBorder()
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(width = 40.dp, height = 48.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Amber600),
                contentAlignment = Alignment.Center
            ) {
                Text(book.abbr, color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Bold)
            }
            Column {
                Text(book.name, fontWeight = FontWeight.SemiBold, fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurface, maxLines = 1)
                Text("${book.chapters} ch.", fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}

// ── Chapters Screen ──────────────────────────────────────────────────────────

@Composable
fun ChaptersScreen(bookId: String, navController: NavController) {
    val book = getBook(bookId) ?: return

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Surface(color = MaterialTheme.colorScheme.surface) {
            Column(Modifier.padding(start = 20.dp, end = 20.dp, top = 52.dp, bottom = 12.dp)) {
                TextButton(
                    onClick = { navController.popBackStack() },
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Icon(Icons.Outlined.ArrowBack, null, tint = Amber600, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(4.dp))
                    Text("Books", color = Amber600, fontSize = 14.sp)
                }
                Spacer(Modifier.height(8.dp))
                Text(book.name, fontSize = 24.sp, fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface)
                Text(
                    "${if (book.testament == "OT") "Old Testament" else "New Testament"} · ${book.chapters} chapters",
                    fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
        HorizontalDivider()

        LazyVerticalGrid(
            columns = GridCells.Fixed(5),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(book.chapters) { i ->
                val ch = i + 1
                Card(
                    onClick = { navController.navigate(Screen.Reader.createRoute(book.id, ch)) },
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.aspectRatio(1f),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(0.dp),
                    border = CardDefaults.outlinedCardBorder()
                ) {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("$ch", fontSize = 14.sp, fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurface)
                    }
                }
            }
        }
    }
}
