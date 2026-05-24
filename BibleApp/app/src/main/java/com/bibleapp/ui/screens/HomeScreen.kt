package com.bibleapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bibleapp.data.*
import com.bibleapp.ui.Screen
import com.bibleapp.ui.theme.Amber600
import com.bibleapp.viewmodel.BibleViewModel
import java.util.Calendar

private fun getGreeting(): String {
    val h = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    return when {
        h < 12 -> "Good morning"
        h < 17 -> "Good afternoon"
        else   -> "Good evening"
    }
}

private val BOOK_COLORS = mapOf(
    "gen" to (Color(0xFF065F46) to Color(0xFFD1FAE5)),
    "psa" to (Color(0xFF1E40AF) to Color(0xFFDBEAFE)),
    "pro" to (Color(0xFF92400E) to Color(0xFFFEF3C7)),
    "mat" to (Color(0xFF6B21A8) to Color(0xFFF3E8FF)),
    "jhn" to (Color(0xFF9F1239) to Color(0xFFFFE4E6)),
    "rom" to (Color(0xFF9A3412) to Color(0xFFFFEDD5)),
)

@Composable
fun HomeScreen(navController: NavController, vm: BibleViewModel) {
    val history by vm.history.collectAsState()
    val bookmarks by vm.bookmarks.collectAsState()
    val daily = getDailyVerse()
    val lastRead = history.firstOrNull()
    val streak = maxOf(1, bookmarks.size + history.size)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // ── Header ──────────────────────────────────────────────────────
        item {
            Surface(color = MaterialTheme.colorScheme.surface, tonalElevation = 0.dp) {
                Row(
                    Modifier.padding(start = 20.dp, end = 20.dp, top = 52.dp, bottom = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(Modifier.weight(1f)) {
                        Text(getGreeting(), color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 13.sp)
                        Text("Open the Word", fontSize = 26.sp, fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(top = 2.dp, bottom = 12.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Chip(icon = Icons.Outlined.LocalFireDepartment, label = "$streak day streak",
                                containerColor = Color(0xFFFFFBEB), contentColor = Color(0xFF92400E))
                            Chip(icon = Icons.Outlined.Star, label = "${bookmarks.size} saved",
                                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                contentColor = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Box(
                            Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .background(Amber600),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Outlined.AutoStories, null, tint = Color.White, modifier = Modifier.size(24.dp))
                        }
                    }
                }
            }
            HorizontalDivider()
        }

        // ── Daily Verse ─────────────────────────────────────────────────
        item {
            Column(Modifier.padding(horizontal = 16.dp, vertical = 20.dp)) {
                SectionLabel("Verse of the Day")
                Spacer(Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .background(
                            Brush.linearGradient(listOf(Color(0xFF92400E), Color(0xFFB45309), Color(0xFFD97706)))
                        )
                        .clickable { navController.navigate(Screen.Reader.createRoute(daily.bookId, daily.chapter)) }
                        .padding(20.dp)
                ) {
                    Column {
                        Text(
                            "\"${daily.text}\"",
                            color = Color.White.copy(alpha = 0.9f),
                            fontSize = 14.sp,
                            fontStyle = FontStyle.Italic,
                            lineHeight = 22.sp,
                            modifier = Modifier.padding(bottom = 12.dp)
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(daily.ref, color = Color(0xFFFDE68A), fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                            Row(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(Color.White.copy(0.2f))
                                    .padding(horizontal = 8.dp, vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Icon(Icons.Outlined.MenuBook, null, tint = Color.White, modifier = Modifier.size(12.dp))
                                Text("Read", color = Color.White, fontSize = 11.sp)
                            }
                        }
                    }
                }
            }
        }

        // ── Continue Reading ────────────────────────────────────────────
        item {
            val resumeBookId = lastRead?.bookId ?: "jhn"
            val resumeBookName = lastRead?.bookName ?: "John"
            val resumeChapter = lastRead?.chapter ?: 3
            Column(Modifier.padding(horizontal = 16.dp).padding(bottom = 20.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        Icon(Icons.Outlined.AccessTime, null, tint = MaterialTheme.colorScheme.outline,
                            modifier = Modifier.size(14.dp))
                        SectionLabel("Continue Reading")
                    }
                    Spacer(Modifier.height(8.dp))
                Card(
                        onClick = { navController.navigate(Screen.Reader.createRoute(resumeBookId, resumeChapter)) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        elevation = CardDefaults.cardElevation(0.dp),
                        border = CardDefaults.outlinedCardBorder()
                    ) {
                        Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            Box(
                                modifier = Modifier.size(width = 48.dp, height = 56.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Amber600),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Outlined.MenuBook, null, tint = Color.White, modifier = Modifier.size(22.dp))
                            }
                            Column(Modifier.weight(1f)) {
                                Text(resumeBookName, fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.onSurface)
                                Text("Chapter $resumeChapter", fontSize = 13.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                            Icon(Icons.Outlined.ChevronRight, null, tint = MaterialTheme.colorScheme.outline)
                        }
                    }
                }
        }

        // ── Popular Books ───────────────────────────────────────────────
        item {
            Column(Modifier.padding(horizontal = 16.dp).padding(bottom = 20.dp)) {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically) {
                    SectionLabel("Popular Books")
                    TextButton(onClick = { navController.navigate(Screen.Books.route) }) {
                        Text("All", color = Amber600, fontSize = 12.sp)
                        Icon(Icons.Outlined.ChevronRight, null, modifier = Modifier.size(14.dp), tint = Amber600)
                    }
                }
                Spacer(Modifier.height(8.dp))
                QUICK_BOOKS.chunked(3).forEachIndexed { rowIndex, rowBooks ->
                    if (rowIndex > 0) Spacer(Modifier.height(10.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxWidth()) {
                        rowBooks.forEach { book ->
                            val (fg, bg) = BOOK_COLORS[book.id] ?: (MaterialTheme.colorScheme.primary to MaterialTheme.colorScheme.primaryContainer)
                            QuickBookChip(book = book, fg = fg, bg = bg,
                                modifier = Modifier.weight(1f),
                                onClick = { navController.navigate(Screen.Chapters.createRoute(book.id)) })
                        }
                    }
                }
            }
        }

        // ── Recent ──────────────────────────────────────────────────────
        if (history.size > 1) {
            item {
                Column(Modifier.padding(horizontal = 16.dp).padding(bottom = 24.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        Icon(Icons.Outlined.AccessTime, null, tint = MaterialTheme.colorScheme.outline,
                            modifier = Modifier.size(13.dp))
                        SectionLabel("Recent")
                    }
                    Spacer(Modifier.height(8.dp))
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        history.drop(1).take(9).forEach { h ->
                            Card(
                                onClick = { navController.navigate(Screen.Reader.createRoute(h.bookId, h.chapter)) },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(14.dp),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                                elevation = CardDefaults.cardElevation(0.dp),
                                border = CardDefaults.outlinedCardBorder()
                            ) {
                                Row(Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                                    Icon(Icons.Outlined.MenuBook, null, tint = MaterialTheme.colorScheme.outline,
                                        modifier = Modifier.size(15.dp))
                                    Text("${h.bookName} ${h.chapter}", fontSize = 14.sp,
                                        color = MaterialTheme.colorScheme.onSurface, modifier = Modifier.weight(1f))
                                    Icon(Icons.Outlined.ChevronRight, null, tint = MaterialTheme.colorScheme.outline,
                                        modifier = Modifier.size(15.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun Chip(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String,
                 containerColor: Color, contentColor: Color) {
    Row(
        modifier = Modifier
            .clip(CircleShape)
            .background(containerColor)
            .padding(horizontal = 12.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Icon(icon, null, tint = contentColor, modifier = Modifier.size(14.dp))
        Text(label, color = contentColor, fontSize = 12.sp, fontWeight = FontWeight.Medium)
    }
}

@Composable
private fun SectionLabel(text: String) {
    Text(text.uppercase(), fontSize = 11.sp, fontWeight = FontWeight.SemiBold,
        letterSpacing = 0.8.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
}

@Composable
private fun QuickBookChip(book: com.bibleapp.data.BibleBook, fg: Color, bg: Color, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(0.dp),
        border = CardDefaults.outlinedCardBorder()
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier.size(40.dp).clip(RoundedCornerShape(10.dp)).background(bg),
                contentAlignment = Alignment.Center
            ) { Text(book.abbr, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = fg) }
            Text(book.name, fontSize = 11.sp, fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface)
        }
    }
}
