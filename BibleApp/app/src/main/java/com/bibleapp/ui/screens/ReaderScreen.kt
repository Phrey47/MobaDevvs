package com.bibleapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bibleapp.data.*
import com.bibleapp.ui.Screen
import com.bibleapp.ui.theme.Amber600
import com.bibleapp.viewmodel.BibleViewModel

private val HIGHLIGHT_COLORS = listOf(
    "yellow" to Color(0xFFFDE68A),
    "green"  to Color(0xFFBBF7D0),
    "blue"   to Color(0xFFBFDBFE),
    "pink"   to Color(0xFFFBCFE8),
)

@Composable
fun ReaderScreen(bookId: String, chapter: Int, navController: NavController, vm: BibleViewModel) {
    val book = getBook(bookId) ?: return
    val verses = remember(bookId, chapter) { getChapterVerses(bookId, chapter) }

    val highlights by vm.highlights.collectAsState()
    val bookmarks by vm.bookmarks.collectAsState()
    val settings  by vm.settings.collectAsState()

    var selectedVerse by remember { mutableStateOf<Verse?>(null) }
    var showNoteInput by remember { mutableStateOf(false) }
    var noteText by remember { mutableStateOf("") }
    var showFontPanel by remember { mutableStateOf(false) }

    LaunchedEffect(bookId, chapter) {
        vm.addHistory(ReadingProgress(bookId, book.name, chapter, System.currentTimeMillis()))
    }

    val bodyFontSize = when (settings.fontSize) {
        "sm" -> 13.sp; "lg" -> 18.sp; "xl" -> 21.sp; else -> 15.sp
    }

    fun verseKey(v: Verse) = "$bookId-$chapter-${v.number}"

    Column(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        // ── Top bar ─────────────────────────────────────────────────────
        Surface(color = MaterialTheme.colorScheme.surface) {
            Row(
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 44.dp, bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Outlined.ArrowBack, null)
                }
                Column(Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(book.name, fontWeight = FontWeight.SemiBold, fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSurface)
                    Text("Chapter $chapter", fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
                IconButton(onClick = { showFontPanel = !showFontPanel }) {
                    Icon(Icons.Outlined.TextFields, null)
                }
            }
        }
        HorizontalDivider()

        // ── Font panel ──────────────────────────────────────────────────
        if (showFontPanel) {
            Surface(color = MaterialTheme.colorScheme.surfaceVariant) {
                Column(Modifier.padding(16.dp)) {
                    Text("Font Size", fontSize = 12.sp, fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Spacer(Modifier.height(8.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        listOf("sm" to "S", "md" to "M", "lg" to "L", "xl" to "XL").forEach { (key, label) ->
                            val selected = settings.fontSize == key
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(if (selected) Amber600 else MaterialTheme.colorScheme.surface)
                                    .clickable { vm.updateSettings { copy(fontSize = key) } }
                                    .padding(horizontal = 16.dp, vertical = 8.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(label, color = if (selected) Color.White else MaterialTheme.colorScheme.onSurface,
                                    fontWeight = FontWeight.Medium)
                            }
                        }
                    }
                }
            }
            HorizontalDivider()
        }

        // ── Verses ──────────────────────────────────────────────────────
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 20.dp)
        ) {
            // Chapter nav row
            item {
                Row(Modifier.fillMaxWidth().padding(bottom = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically) {
                    if (chapter > 1) {
                        IconButton(onClick = {
                            navController.navigate(Screen.Reader.createRoute(bookId, chapter - 1)) {
                                popUpTo(Screen.Reader.route) { inclusive = true }
                            }
                        }) { Icon(Icons.Outlined.ChevronLeft, "Previous", tint = Amber600) }
                    } else { Spacer(Modifier.size(48.dp)) }

                    Text("$chapter", fontSize = 28.sp, fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface)

                    if (chapter < book.chapters) {
                        IconButton(onClick = {
                            navController.navigate(Screen.Reader.createRoute(bookId, chapter + 1)) {
                                popUpTo(Screen.Reader.route) { inclusive = true }
                            }
                        }) { Icon(Icons.Outlined.ChevronRight, "Next", tint = Amber600) }
                    } else { Spacer(Modifier.size(48.dp)) }
                }
            }

            items(verses) { verse ->
                val key = verseKey(verse)
                val hlColor = highlights[key]?.let { name ->
                    HIGHLIGHT_COLORS.find { it.first == name }?.second
                }
                val isSelected = selectedVerse?.number == verse.number

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(
                            when {
                                isSelected -> Amber600.copy(alpha = 0.08f)
                                hlColor != null -> hlColor.copy(alpha = 0.5f)
                                else -> Color.Transparent
                            }
                        )
                        .clickable {
                            selectedVerse = if (isSelected) null else verse
                            showNoteInput = false
                        }
                        .padding(vertical = 6.dp, horizontal = 8.dp)
                ) {
                    Text(
                        "${verse.number} ",
                        fontSize = (bodyFontSize.value - 2).sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Amber600,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                    Text(
                        verse.text,
                        fontSize = bodyFontSize,
                        color = MaterialTheme.colorScheme.onBackground,
                        lineHeight = (bodyFontSize.value * 1.7f).sp
                    )
                }
                Spacer(Modifier.height(4.dp))
            }

            item { Spacer(Modifier.height(80.dp)) }
        }

        // ── Verse action toolbar ────────────────────────────────────────
        if (selectedVerse != null) {
            val v = selectedVerse!!
            val key = verseKey(v)
            val bmked = bookmarks.containsKey(key)

            Surface(
                tonalElevation = 4.dp,
                shadowElevation = 8.dp,
                color = MaterialTheme.colorScheme.surface
            ) {
                Column(Modifier.padding(16.dp)) {
                    // Action buttons
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                        ActionBtn(
                            icon = if (bmked) Icons.Outlined.BookmarkAdded else Icons.Outlined.BookmarkBorder,
                            label = if (bmked) "Saved" else "Bookmark",
                            tint = if (bmked) Amber600 else MaterialTheme.colorScheme.onSurface
                        ) {
                            if (bmked) vm.removeBookmark(key)
                            else vm.addBookmark(Bookmark(key, bookId, book.name, chapter, v.number, v.text, System.currentTimeMillis()))
                        }
                        ActionBtn(Icons.Outlined.Edit, "Note") { showNoteInput = !showNoteInput }
                        ActionBtn(Icons.Outlined.ContentCopy, "Copy") { /* copy to clipboard */ }
                        ActionBtn(Icons.Outlined.Close, "Dismiss") { selectedVerse = null }
                    }

                    // Highlight row
                    Spacer(Modifier.height(12.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        Text("Highlight:", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.align(Alignment.CenterVertically))
                        HIGHLIGHT_COLORS.forEach { (name, color) ->
                            val active = highlights[key] == name
                            Box(
                                modifier = Modifier
                                    .size(28.dp)
                                    .clip(CircleShape)
                                    .background(color)
                                    .clickable {
                                        if (active) vm.removeHighlight(key)
                                        else vm.setHighlight(key, name)
                                    }
                                    .then(if (active) Modifier.padding(4.dp) else Modifier)
                            )
                        }
                    }

                    // Note input
                    if (showNoteInput) {
                        val existing = vm.getNote(key)
                        LaunchedEffect(key) { noteText = existing?.content ?: "" }
                        Spacer(Modifier.height(12.dp))
                        OutlinedTextField(
                            value = noteText,
                            onValueChange = { noteText = it },
                            placeholder = { Text("Add a note…", fontSize = 13.sp) },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            maxLines = 4,
                            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Amber600)
                        )
                        Spacer(Modifier.height(8.dp))
                        Button(
                            onClick = {
                                if (noteText.isNotBlank()) {
                                    vm.saveNote(Note(key, bookId, book.name, chapter, v.number, v.text, noteText, System.currentTimeMillis()))
                                } else {
                                    vm.removeNote(key)
                                }
                                showNoteInput = false
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Amber600),
                            shape = RoundedCornerShape(10.dp)
                        ) { Text("Save Note") }
                    }
                }
            }
        }
    }
}

@Composable
private fun ActionBtn(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String,
                      tint: Color = Color.Unspecified, onClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        IconButton(onClick = onClick) {
            Icon(icon, null, tint = if (tint == Color.Unspecified)
                LocalContentColor.current else tint)
        }
        Text(label, fontSize = 10.sp, color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center)
    }
}
