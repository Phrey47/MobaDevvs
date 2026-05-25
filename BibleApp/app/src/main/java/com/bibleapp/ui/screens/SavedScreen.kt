package com.bibleapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bibleapp.ui.Screen
import com.bibleapp.ui.theme.Amber600
import com.bibleapp.viewmodel.BibleViewModel

@Composable
fun SavedScreen(navController: NavController, vm: BibleViewModel) {
    var tab by remember { mutableStateOf(0) }
    val bookmarks by vm.bookmarks.collectAsState()
    val notes by vm.notes.collectAsState()
    val highlights by vm.highlights.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Surface(color = MaterialTheme.colorScheme.surface) {
            Column(Modifier.padding(start = 20.dp, end = 20.dp, top = 52.dp, bottom = 4.dp)) {
                Text("Bookmarks", fontSize = 24.sp, fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(bottom = 12.dp))
                TabRow(
                    selectedTabIndex = tab,
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = Amber600
                ) {
                    Tab(selected = tab == 0, onClick = { tab = 0 }) {
                        Row(Modifier.padding(vertical = 12.dp), verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            Icon(Icons.Outlined.Bookmark, null, modifier = Modifier.size(16.dp))
                            Text("Bookmarks (${bookmarks.size})", fontSize = 13.sp)
                        }
                    }
                    Tab(selected = tab == 1, onClick = { tab = 1 }) {
                        Row(Modifier.padding(vertical = 12.dp), verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            Icon(Icons.Outlined.Notes, null, modifier = Modifier.size(16.dp))
                            Text("Notes (${notes.size})", fontSize = 13.sp)
                        }
                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .padding(horizontal = 20.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AssistChip(onClick = { tab = 0 }, label = { Text("Newest", fontSize = 12.sp) }, leadingIcon = {
                Icon(Icons.Outlined.Sort, null, modifier = Modifier.size(14.dp))
            })
            AssistChip(onClick = { }, label = { Text("All saved", fontSize = 12.sp) }, leadingIcon = {
                Icon(Icons.Outlined.FilterList, null, modifier = Modifier.size(14.dp))
            })
            Spacer(Modifier.weight(1f))
            IconButton(onClick = { }) {
                Icon(Icons.Outlined.MoreHoriz, null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
        HorizontalDivider()

        if (tab == 0) {
            val bmList = bookmarks.values.sortedByDescending { it.createdAt }
            if (bmList.isEmpty()) {
                EmptyState(icon = Icons.Outlined.BookmarkBorder,
                    message = "No bookmarks yet", sub = "Tap a verse while reading to bookmark it")
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(bmList, key = { it.key }) { bm ->
                        val hasNote = notes.containsKey(bm.key)
                        val highlightColor = colorForHighlight(highlights[bm.key])
                        Card(
                            onClick = { navController.navigate(Screen.Reader.createRoute(bm.bookId, bm.chapter)) },
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            elevation = CardDefaults.cardElevation(0.dp),
                            border = CardDefaults.outlinedCardBorder()
                        ) {
                            Row {
                                Box(
                                    Modifier
                                        .width(5.dp)
                                        .fillMaxHeight()
                                        .background(highlightColor ?: Color.Transparent)
                                )
                            Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Outlined.Bookmark, null, tint = Amber600, modifier = Modifier.size(22.dp))
                                Spacer(Modifier.width(12.dp))
                                Column(Modifier.weight(1f)) {
                                    Text("${bm.bookName} ${bm.chapter}:${bm.verse}",
                                        fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Amber600)
                                    Spacer(Modifier.height(4.dp))
                                    Text(bm.text, fontSize = 14.sp, lineHeight = 20.sp,
                                        color = MaterialTheme.colorScheme.onSurface, maxLines = 3)
                                    if (hasNote) {
                                        Row(
                                            Modifier.padding(top = 8.dp),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                                        ) {
                                            Icon(Icons.Outlined.StickyNote2, null, modifier = Modifier.size(14.dp), tint = MaterialTheme.colorScheme.onSurfaceVariant)
                                            Text("Note attached", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                        }
                                    }
                                }
                                IconButton(onClick = { vm.removeBookmark(bm.key) }) {
                                    Icon(Icons.Outlined.MoreHoriz, "Options",
                                        tint = MaterialTheme.colorScheme.onSurfaceVariant)
                                }
                            }
                            }
                        }
                    }
                }
            }
        } else {
            val noteList = notes.values.sortedByDescending { it.createdAt }
            if (noteList.isEmpty()) {
                EmptyState(icon = Icons.Outlined.EditNote,
                    message = "No notes yet", sub = "Add notes to verses while reading")
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(noteList, key = { it.key }) { note ->
                        Card(
                            onClick = { navController.navigate(Screen.Reader.createRoute(note.bookId, note.chapter)) },
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            elevation = CardDefaults.cardElevation(0.dp),
                            border = CardDefaults.outlinedCardBorder()
                        ) {
                            Column(Modifier.padding(16.dp)) {
                                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically) {
                                    Text("${note.bookName} ${note.chapter}:${note.verse}",
                                        fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Amber600)
                                    IconButton(onClick = { vm.removeNote(note.key) },
                                        modifier = Modifier.size(32.dp)) {
                                        Icon(Icons.Outlined.DeleteOutline, "Delete",
                                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                            modifier = Modifier.size(18.dp))
                                    }
                                }
                                Text(note.verseText, fontSize = 13.sp, lineHeight = 19.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.padding(top = 4.dp, bottom = 8.dp))
                                HorizontalDivider()
                                Text(note.content, fontSize = 14.sp, lineHeight = 20.sp,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.padding(top = 8.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}

private fun colorForHighlight(name: String?): Color? = when (name) {
    "yellow" -> Color(0xFFFDE68A)
    "green" -> Color(0xFFBBF7D0)
    "blue" -> Color(0xFFBFDBFE)
    "pink" -> Color(0xFFFBCFE8)
    else -> null
}

@Composable
private fun EmptyState(icon: androidx.compose.ui.graphics.vector.ImageVector, message: String, sub: String) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Icon(icon, null, modifier = Modifier.size(56.dp), tint = MaterialTheme.colorScheme.outlineVariant)
            Text(message, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.onSurface)
            Text(sub, fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}
