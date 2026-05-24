package com.bibleapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bibleapp.data.Bookmark
import com.bibleapp.data.POPULAR_SEARCHES
import com.bibleapp.data.getBook
import com.bibleapp.data.searchVerses
import com.bibleapp.ui.Screen
import com.bibleapp.ui.theme.Amber600
import com.bibleapp.viewmodel.BibleViewModel

@Composable
fun SearchScreen(navController: NavController, vm: BibleViewModel) {
    var query by remember { mutableStateOf("") }
    var selectedFilter by remember { mutableStateOf("All") }
    val recentSearch by vm.recentSearch.collectAsState()
    val bookmarks by vm.bookmarks.collectAsState()

    val results = remember(query, selectedFilter) {
        val base = if (query.trim().length > 1) searchVerses(query) else emptyList()
        when (selectedFilter) {
            "OT" -> base.filter { getBook(it.bookId)?.testament == "OT" }
            "NT" -> base.filter { getBook(it.bookId)?.testament == "NT" }
            "Topic" -> base.filter { result ->
                POPULAR_SEARCHES.any { result.text.contains(it, ignoreCase = true) }
            }
            else -> base
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Header
        Surface(color = MaterialTheme.colorScheme.surface) {
            Column(Modifier.padding(start = 16.dp, end = 16.dp, top = 52.dp, bottom = 12.dp)) {
                Text("Search", fontSize = 24.sp, fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(bottom = 12.dp))
                OutlinedTextField(
                    value = query,
                    onValueChange = {
                        query = it
                        if (it.trim().length > 1) vm.addRecentSearch(it.trim())
                    },
                    placeholder = { Text("Search Scripture…", fontSize = 14.sp) },
                    leadingIcon = { Icon(Icons.Outlined.Search, null, modifier = Modifier.size(18.dp)) },
                    trailingIcon = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            if (query.isNotEmpty()) {
                                IconButton(onClick = { query = "" }) {
                                    Icon(Icons.Outlined.Clear, null, modifier = Modifier.size(16.dp))
                                }
                            }
                            Icon(Icons.Outlined.FilterList, null, modifier = Modifier.size(18.dp))
                            Spacer(Modifier.width(12.dp))
                        }
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Amber600)
                )
                Spacer(Modifier.height(10.dp))
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    listOf("All", "OT", "NT", "Verse", "Topic").forEach { filter ->
                        val selected = selectedFilter == filter
                        FilterChip(
                            selected = selected,
                            onClick = { selectedFilter = filter },
                            label = { Text(filter, fontSize = 12.sp) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = Amber600,
                                selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                            )
                        )
                    }
                }
            }
        }
        HorizontalDivider()

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            if (query.trim().length <= 1) {
                // Popular searches
                item {
                    Text("Popular Searches", fontSize = 11.sp, fontWeight = FontWeight.SemiBold,
                        letterSpacing = 0.8.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Spacer(Modifier.height(10.dp))
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(POPULAR_SEARCHES) { term ->
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(20.dp))
                                    .background(MaterialTheme.colorScheme.surfaceVariant)
                                    .clickable { query = term }
                                    .padding(horizontal = 14.dp, vertical = 8.dp)
                            ) {
                                Text(term, fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                        }
                    }
                    Spacer(Modifier.height(20.dp))
                }

                // Recent
                if (recentSearch.isNotEmpty()) {
                    item {
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically) {
                            Row(verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                Icon(Icons.Outlined.History, null,
                                    modifier = Modifier.size(14.dp),
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant)
                                Text("Recent", fontSize = 11.sp, fontWeight = FontWeight.SemiBold,
                                    letterSpacing = 0.8.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                            TextButton(onClick = { vm.clearRecentSearch() }) {
                                Text("Clear", fontSize = 12.sp, color = Amber600)
                            }
                        }
                    }
                    items(recentSearch) { term ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { query = term }
                                .padding(vertical = 10.dp, horizontal = 4.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Icon(Icons.Outlined.History, null, modifier = Modifier.size(16.dp),
                                tint = MaterialTheme.colorScheme.onSurfaceVariant)
                            Text(term, fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.weight(1f))
                            Icon(Icons.Outlined.ArrowOutward, null, modifier = Modifier.size(14.dp),
                                tint = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                        HorizontalDivider()
                    }
                }
            } else {
                // Results
                item {
                    if (selectedFilter != "All") {
                        InputChip(
                            selected = true,
                            onClick = { selectedFilter = "All" },
                            label = { Text(selectedFilter, fontSize = 12.sp) },
                            trailingIcon = { Icon(Icons.Outlined.Close, null, modifier = Modifier.size(14.dp)) },
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }
                    Text("${results.size} result${if (results.size != 1) "s" else ""} for \"$query\"",
                        fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(bottom = 12.dp))
                }
                items(results) { result ->
                    val key = "${result.bookId}-${result.chapter}-${result.verse}"
                    val bookmarked = bookmarks.containsKey(key)
                    Card(
                        onClick = { navController.navigate(Screen.Reader.createRoute(result.bookId, result.chapter)) },
                        modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        elevation = CardDefaults.cardElevation(0.dp),
                        border = CardDefaults.outlinedCardBorder()
                    ) {
                        Row(Modifier.padding(16.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            Icon(Icons.Outlined.MenuBook, null, modifier = Modifier.size(20.dp), tint = Amber600)
                            Column(Modifier.weight(1f)) {
                                Text("${result.bookName} ${result.chapter}:${result.verse}",
                                    fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Amber600)
                            Spacer(Modifier.height(4.dp))
                            Text(
                                buildAnnotatedString {
                                    val lq = query.lowercase()
                                    val lt = result.text.lowercase()
                                    var idx = 0
                                    var found = lt.indexOf(lq, idx)
                                    while (found != -1) {
                                        append(result.text.substring(idx, found))
                                        withStyle(SpanStyle(background = Amber600.copy(0.25f), fontWeight = FontWeight.SemiBold)) {
                                            append(result.text.substring(found, found + query.length))
                                        }
                                        idx = found + query.length
                                        found = lt.indexOf(lq, idx)
                                    }
                                    append(result.text.substring(idx))
                                },
                                fontSize = 14.sp, lineHeight = 22.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            }
                            IconButton(onClick = {
                                if (bookmarked) vm.removeBookmark(key)
                                else vm.addBookmark(
                                    Bookmark(
                                        key = key,
                                        bookId = result.bookId,
                                        bookName = result.bookName,
                                        chapter = result.chapter,
                                        verse = result.verse,
                                        text = result.text,
                                        createdAt = System.currentTimeMillis()
                                    )
                                )
                            }) {
                                Icon(
                                    if (bookmarked) Icons.Outlined.BookmarkAdded else Icons.Outlined.BookmarkBorder,
                                    null,
                                    tint = if (bookmarked) Amber600 else MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }
                if (results.isEmpty()) {
                    item {
                        Box(Modifier.fillMaxWidth().padding(top = 48.dp), contentAlignment = Alignment.Center) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(Icons.Outlined.SearchOff, null, modifier = Modifier.size(48.dp),
                                    tint = MaterialTheme.colorScheme.outlineVariant)
                                Spacer(Modifier.height(12.dp))
                                Text("No results found", color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                        }
                    }
                }
            }
        }
    }
}
