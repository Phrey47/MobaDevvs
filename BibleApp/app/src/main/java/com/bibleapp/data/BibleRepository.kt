package com.bibleapp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

data class Bookmark(
    val key: String,
    val bookId: String,
    val bookName: String,
    val chapter: Int,
    val verse: Int,
    val text: String,
    val createdAt: Long
)

data class Note(
    val key: String,
    val bookId: String,
    val bookName: String,
    val chapter: Int,
    val verse: Int,
    val verseText: String,
    val content: String,
    val createdAt: Long
)

data class ReadingProgress(
    val bookId: String,
    val bookName: String,
    val chapter: Int,
    val lastRead: Long
)

data class AppSettings(
    val theme: String = "light",   // light | dark | sepia
    val fontSize: String = "md",   // sm | md | lg | xl
    val translation: String = "KJV"
)

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "bible_prefs")

class BibleRepository(private val context: Context) {
    private val gson = Gson()

    private val KEY_BOOKMARKS = stringPreferencesKey("bookmarks")
    private val KEY_HIGHLIGHTS = stringPreferencesKey("highlights")
    private val KEY_NOTES = stringPreferencesKey("notes")
    private val KEY_HISTORY = stringPreferencesKey("history")
    private val KEY_SETTINGS = stringPreferencesKey("settings")
    private val KEY_RECENT_SEARCH = stringPreferencesKey("recent_search")

    val bookmarksFlow: Flow<Map<String, Bookmark>> = context.dataStore.data.map { prefs ->
        val json = prefs[KEY_BOOKMARKS] ?: return@map emptyMap()
        val type = object : TypeToken<Map<String, Bookmark>>() {}.type
        gson.fromJson(json, type) ?: emptyMap()
    }

    val highlightsFlow: Flow<Map<String, String>> = context.dataStore.data.map { prefs ->
        val json = prefs[KEY_HIGHLIGHTS] ?: return@map emptyMap()
        val type = object : TypeToken<Map<String, String>>() {}.type
        gson.fromJson(json, type) ?: emptyMap()
    }

    val notesFlow: Flow<Map<String, Note>> = context.dataStore.data.map { prefs ->
        val json = prefs[KEY_NOTES] ?: return@map emptyMap()
        val type = object : TypeToken<Map<String, Note>>() {}.type
        gson.fromJson(json, type) ?: emptyMap()
    }

    val historyFlow: Flow<List<ReadingProgress>> = context.dataStore.data.map { prefs ->
        val json = prefs[KEY_HISTORY] ?: return@map emptyList()
        val type = object : TypeToken<List<ReadingProgress>>() {}.type
        gson.fromJson(json, type) ?: emptyList()
    }

    val settingsFlow: Flow<AppSettings> = context.dataStore.data.map { prefs ->
        val json = prefs[KEY_SETTINGS] ?: return@map AppSettings()
        gson.fromJson(json, AppSettings::class.java) ?: AppSettings()
    }

    val recentSearchFlow: Flow<List<String>> = context.dataStore.data.map { prefs ->
        val json = prefs[KEY_RECENT_SEARCH] ?: return@map emptyList()
        val type = object : TypeToken<List<String>>() {}.type
        gson.fromJson(json, type) ?: emptyList()
    }

    suspend fun saveBookmarks(bookmarks: Map<String, Bookmark>) {
        context.dataStore.edit { it[KEY_BOOKMARKS] = gson.toJson(bookmarks) }
    }

    suspend fun saveHighlights(highlights: Map<String, String>) {
        context.dataStore.edit { it[KEY_HIGHLIGHTS] = gson.toJson(highlights) }
    }

    suspend fun saveNotes(notes: Map<String, Note>) {
        context.dataStore.edit { it[KEY_NOTES] = gson.toJson(notes) }
    }

    suspend fun saveHistory(history: List<ReadingProgress>) {
        context.dataStore.edit { it[KEY_HISTORY] = gson.toJson(history) }
    }

    suspend fun saveSettings(settings: AppSettings) {
        context.dataStore.edit { it[KEY_SETTINGS] = gson.toJson(settings) }
    }

    suspend fun saveRecentSearch(searches: List<String>) {
        context.dataStore.edit { it[KEY_RECENT_SEARCH] = gson.toJson(searches) }
    }
}
