package com.bibleapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.bibleapp.data.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class BibleViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = BibleRepository(app)

    val bookmarks: StateFlow<Map<String, Bookmark>> = repo.bookmarksFlow
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyMap())

    val highlights: StateFlow<Map<String, String>> = repo.highlightsFlow
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyMap())

    val notes: StateFlow<Map<String, Note>> = repo.notesFlow
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyMap())

    val history: StateFlow<List<ReadingProgress>> = repo.historyFlow
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    val settings: StateFlow<AppSettings> = repo.settingsFlow
        .stateIn(viewModelScope, SharingStarted.Eagerly, AppSettings())

    val recentSearch: StateFlow<List<String>> = repo.recentSearchFlow
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    // ── Bookmarks ──────────────────────────────────────────────────────────
    fun addBookmark(bm: Bookmark) = viewModelScope.launch {
        repo.saveBookmarks(bookmarks.value + (bm.key to bm))
    }

    fun removeBookmark(key: String) = viewModelScope.launch {
        repo.saveBookmarks(bookmarks.value - key)
    }

    fun isBookmarked(key: String) = bookmarks.value.containsKey(key)

    // ── Highlights ─────────────────────────────────────────────────────────
    fun setHighlight(key: String, color: String) = viewModelScope.launch {
        repo.saveHighlights(highlights.value + (key to color))
    }

    fun removeHighlight(key: String) = viewModelScope.launch {
        repo.saveHighlights(highlights.value - key)
    }

    fun getHighlight(key: String): String? = highlights.value[key]

    // ── Notes ──────────────────────────────────────────────────────────────
    fun saveNote(note: Note) = viewModelScope.launch {
        repo.saveNotes(notes.value + (note.key to note))
    }

    fun removeNote(key: String) = viewModelScope.launch {
        repo.saveNotes(notes.value - key)
    }

    fun getNote(key: String): Note? = notes.value[key]

    // ── History ────────────────────────────────────────────────────────────
    fun addHistory(entry: ReadingProgress) = viewModelScope.launch {
        val filtered = history.value.filter {
            !(it.bookId == entry.bookId && it.chapter == entry.chapter)
        }
        repo.saveHistory((listOf(entry) + filtered).take(10))
    }

    // ── Settings ───────────────────────────────────────────────────────────
    fun updateSettings(block: AppSettings.() -> AppSettings) = viewModelScope.launch {
        repo.saveSettings(settings.value.block())
    }

    // ── Recent search ──────────────────────────────────────────────────────
    fun addRecentSearch(query: String) = viewModelScope.launch {
        val updated = (listOf(query) + recentSearch.value.filter { it != query }).take(8)
        repo.saveRecentSearch(updated)
    }

    fun clearRecentSearch() = viewModelScope.launch {
        repo.saveRecentSearch(emptyList())
    }
}
