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
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
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
    "green" to Color(0xFFBBF7D0),
    "blue" to Color(0xFFBFDBFE),
    "pink" to Color(0xFFFBCFE8),
)

@Composable
fun ReaderScreen(bookId: String, chapter: Int, navController: NavController, vm: BibleViewModel) {
    val book = getBook(bookId) ?: return
    val verses = remember(bookId, chapter) { getChapterVerses(bookId, chapter) }

    val highlights by vm.highlights.collectAsState()
    val bookmarks by vm.bookmarks.collectAsState()
    val settings by vm.settings.collectAsState()
    val clipboard = LocalClipboardManager.current

    var selectedVerse by remember { mutableStateOf<Verse?>(null) }
    var showNoteInput by remember { mutableStateOf(false) }
    var noteText by remember { mutableStateOf("") }
    var showDisplayPanel by remember { mutableStateOf(false) }
    var showBookMenu by remember { mutableStateOf(false) }
    var showChapterMenu by remember { mutableStateOf(false) }

    LaunchedEffect(bookId, chapter) {
        vm.addHistory(ReadingProgress(bookId, book.name, chapter, System.currentTimeMillis()))
    }

    val bodyFontSize = when (settings.fontSize) {
        "sm" -> 13.sp
        "lg" -> 18.sp
        "xl" -> 21.sp
        else -> 15.sp
    }

    fun verseKey(v: Verse) = "$bookId-$chapter-${v.number}"

    Column(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        Surface(color = MaterialTheme.colorScheme.surface) {
            Row(
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 44.dp, bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Outlined.ArrowBack, "Back")
                }
                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box {
                        TextButton(onClick = { showBookMenu = true }) {
                            Text(book.name, fontWeight = FontWeight.SemiBold, fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurface)
                            Icon(Icons.Outlined.ArrowDropDown, null)
                        }
                        DropdownMenu(expanded = showBookMenu, onDismissRequest = { showBookMenu = false }) {
                            ALL_BOOKS.forEach { option ->
                                DropdownMenuItem(
                                    text = { Text(option.name) },
                                    onClick = {
                                        showBookMenu = false
                                        selectedVerse = null
                                        navController.navigate(Screen.Reader.createRoute(option.id, 1))
                                    }
                                )
                            }
                        }
                    }
                    Box {
                        TextButton(onClick = { showChapterMenu = true }) {
                            Text("Ch. $chapter", fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurface)
                            Icon(Icons.Outlined.ArrowDropDown, null)
                        }
                        DropdownMenu(expanded = showChapterMenu, onDismissRequest = { showChapterMenu = false }) {
                            (1..book.chapters).forEach { option ->
                                DropdownMenuItem(
                                    text = { Text("Chapter $option") },
                                    onClick = {
                                        showChapterMenu = false
                                        selectedVerse = null
                                        navController.navigate(Screen.Reader.createRoute(bookId, option))
                                    }
                                )
                            }
                        }
                    }
                }
                IconButton(onClick = { showDisplayPanel = !showDisplayPanel }) {
                    Icon(Icons.Outlined.TextFields, "Display settings")
                }
                IconButton(onClick = { clipboard.setText(AnnotatedString("${book.name} $chapter")) }) {
                    Icon(Icons.Outlined.Share, "Share")
                }
            }
        }
        HorizontalDivider()

        if (showDisplayPanel) {
            Surface(color = MaterialTheme.colorScheme.surfaceVariant) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("Font Size", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        listOf("sm" to "S", "md" to "M", "lg" to "L", "xl" to "XL").forEach { (key, label) ->
                            val selected = settings.fontSize == key
                            FilterChip(
                                selected = selected,
                                onClick = { vm.updateSettings { copy(fontSize = key) } },
                                label = { Text(label) },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = Amber600,
                                    selectedLabelColor = Color.White
                                )
                            )
                        }
                    }
                }
            }
            HorizontalDivider()
        }

        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 20.dp)
        ) {
            item {
                Row(
                    Modifier.fillMaxWidth().padding(bottom = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        settings.translation,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Amber600,
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primaryContainer)
                            .clickable {
                                vm.updateSettings {
                                    val next = when (translation) {
                                        "KJV" -> "NIV"
                                        "NIV" -> "ESV"
                                        "ESV" -> "NLT"
                                        else -> "KJV"
                                    }
                                    copy(translation = next)
                                }
                            }
                            .padding(horizontal = 10.dp, vertical = 5.dp)
                    )
                    Text("${book.name} $chapter", fontSize = 26.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                    Spacer(Modifier.width(52.dp))
                }
            }

            items(verses) { verse ->
                val key = verseKey(verse)
                val highlightColor = highlights[key]?.let { name -> HIGHLIGHT_COLORS.find { it.first == name }?.second }
                val isBookmarked = bookmarks.containsKey(key)
                val isSelected = selectedVerse?.number == verse.number

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(
                            when {
                                isSelected -> Amber600.copy(alpha = 0.08f)
                                highlightColor != null -> highlightColor.copy(alpha = 0.5f)
                                else -> Color.Transparent
                            }
                        )
                        .clickable {
                            selectedVerse = if (isSelected) null else verse
                            showNoteInput = false
                        }
                        .padding(vertical = 7.dp, horizontal = 8.dp),
                    verticalAlignment = Alignment.Top
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
                        lineHeight = (bodyFontSize.value * 1.7f).sp,
                        modifier = Modifier.weight(1f)
                    )
                    if (isBookmarked) {
                        Icon(Icons.Outlined.Bookmark, null, tint = Amber600, modifier = Modifier.size(18.dp))
                    }
                }
                Spacer(Modifier.height(4.dp))
            }

            item { Spacer(Modifier.height(48.dp)) }
        }

        Surface(color = MaterialTheme.colorScheme.surface, tonalElevation = 2.dp) {
            Row(
                Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    enabled = chapter > 1,
                    onClick = { navController.navigate(Screen.Reader.createRoute(bookId, chapter - 1)) }
                ) {
                    Icon(Icons.Outlined.ChevronLeft, "Previous", tint = if (chapter > 1) Amber600 else MaterialTheme.colorScheme.outline)
                }
                Text("Chapter $chapter", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.onSurface)
                IconButton(
                    enabled = chapter < book.chapters,
                    onClick = { navController.navigate(Screen.Reader.createRoute(bookId, chapter + 1)) }
                ) {
                    Icon(Icons.Outlined.ChevronRight, "Next", tint = if (chapter < book.chapters) Amber600 else MaterialTheme.colorScheme.outline)
                }
            }
        }

        if (selectedVerse != null) {
            val verse = selectedVerse!!
            val key = verseKey(verse)
            val bookmarked = bookmarks.containsKey(key)

            Surface(tonalElevation = 4.dp, shadowElevation = 8.dp, color = MaterialTheme.colorScheme.surface) {
                Column(Modifier.padding(16.dp)) {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                        ActionBtn(
                            icon = if (bookmarked) Icons.Outlined.BookmarkAdded else Icons.Outlined.BookmarkBorder,
                            label = if (bookmarked) "Saved" else "Bookmark",
                            tint = if (bookmarked) Amber600 else MaterialTheme.colorScheme.onSurface
                        ) {
                            if (bookmarked) {
                                vm.removeBookmark(key)
                            } else {
                                vm.addBookmark(Bookmark(key, bookId, book.name, chapter, verse.number, verse.text, System.currentTimeMillis()))
                            }
                        }
                        ActionBtn(Icons.Outlined.Edit, "Note") { showNoteInput = !showNoteInput }
                        ActionBtn(Icons.Outlined.ContentCopy, "Copy") {
                            clipboard.setText(AnnotatedString("${book.name} $chapter:${verse.number} ${verse.text}"))
                        }
                        ActionBtn(Icons.Outlined.Close, "Dismiss") { selectedVerse = null }
                    }

                    Spacer(Modifier.height(12.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.CenterVertically) {
                        Text("Highlight:", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        HIGHLIGHT_COLORS.forEach { (name, color) ->
                            val active = highlights[key] == name
                            Box(
                                modifier = Modifier
                                    .size(if (active) 32.dp else 28.dp)
                                    .clip(CircleShape)
                                    .background(color)
                                    .clickable {
                                        if (active) vm.removeHighlight(key) else vm.setHighlight(key, name)
                                    }
                            )
                        }
                    }

                    if (showNoteInput) {
                        val existing = vm.getNote(key)
                        LaunchedEffect(key) { noteText = existing?.content ?: "" }
                        Spacer(Modifier.height(12.dp))
                        OutlinedTextField(
                            value = noteText,
                            onValueChange = { noteText = it },
                            placeholder = { Text("Add a note...", fontSize = 13.sp) },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            maxLines = 4,
                            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Amber600)
                        )
                        Spacer(Modifier.height(8.dp))
                        Button(
                            onClick = {
                                if (noteText.isNotBlank()) {
                                    vm.saveNote(Note(key, bookId, book.name, chapter, verse.number, verse.text, noteText, System.currentTimeMillis()))
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
private fun ActionBtn(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    tint: Color = Color.Unspecified,
    onClick: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        IconButton(onClick = onClick) {
            Icon(icon, null, tint = if (tint == Color.Unspecified) LocalContentColor.current else tint)
        }
        Text(label, fontSize = 10.sp, color = MaterialTheme.colorScheme.onSurfaceVariant, textAlign = TextAlign.Center)
    }
}
