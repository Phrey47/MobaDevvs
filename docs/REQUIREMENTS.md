# 📋 Mobile Bible App — Requirements Documentation

> Version 1.0 — May 2026

---

## Table of Contents
1. [Functional Requirements](#1-functional-requirements)
2. [Non-Functional Requirements](#2-non-functional-requirements)
3. [Technical Requirements](#3-technical-requirements)
4. [Constraints](#4-constraints)
5. [Future Requirements](#5-future-requirements)

---

## 1. Functional Requirements

### 1.1 Bible Reading
- The app shall display all 66 books of the Bible (39 OT, 27 NT)
- The app shall allow users to navigate books, chapters, and verses
- The app shall support chapter-by-chapter reading with previous/next navigation
- The app shall display verse numbers alongside verse text
- The app shall remember the last chapter read and allow users to continue reading

### 1.2 Verse Interactions
- The app shall allow users to tap a verse to select it
- The app shall allow users to bookmark selected verses
- The app shall allow users to highlight selected verses in 4 colors (Yellow, Green, Blue, Pink)
- The app shall allow users to add personal notes to any verse
- The app shall allow users to remove bookmarks, highlights, and notes
- The app shall allow users to copy verse text to clipboard

### 1.3 Home Screen
- The app shall display a daily verse that changes every day
- The app shall display a reading streak counter
- The app shall display quick access to popular Bible books
- The app shall display the last read chapter for quick resume
- The app shall display recent reading history (last 10 chapters)

### 1.4 Search
- The app shall provide full-text search across all stored Bible verses
- The app shall highlight the search term inside results
- The app shall display popular search suggestions
- The app shall save and display recent search history
- The app shall allow users to clear recent search history

### 1.5 Saved Items
- The app shall display all bookmarked verses in a dedicated tab
- The app shall display all notes in a dedicated tab
- The app shall allow navigation to the source chapter from any saved item
- The app shall allow deletion of individual bookmarks and notes

### 1.6 Settings
- The app shall allow users to switch between Light, Dark, and Sepia themes
- The app shall allow users to adjust font size (Small, Medium, Large, Extra Large)
- The app shall allow users to switch Bible translations (KJV, NIV, ESV, NLT)

---

## 2. Non-Functional Requirements

### 2.1 Performance
- The app shall launch within 3 seconds on supported devices
- Screen transitions shall be smooth with no visible lag
- Search results shall appear within 1 second of input

### 2.2 Usability
- The app shall follow Material Design 3 guidelines
- All screens shall be accessible via the bottom navigation bar
- The app shall be usable with one hand on a standard smartphone

### 2.3 Compatibility
- The app shall support Android 5.0 and above (API 21+)
- The app shall support screen densities from mdpi to xxxhdpi
- The app shall support portrait orientation

### 2.4 Data & Storage
- All user data (bookmarks, highlights, notes, settings) shall be stored locally
- User data shall persist between app sessions and restarts
- No internet connection shall be required to use the app

### 2.5 Security & Privacy
- The app shall not collect or transmit any user data
- All data shall remain on the user's device only
- No login or account creation shall be required

---

## 3. Technical Requirements

### 3.1 Development

| Item | Details |
|---|---|
| Language | Kotlin |
| UI Framework | Jetpack Compose |
| Architecture | MVVM (Model-View-ViewModel) |
| Minimum SDK | API 21 (Android 5.0) |
| Target SDK | API 35 (Android 15) |
| Android Gradle Plugin | 8.7.3 |
| Kotlin Version | 2.0.0 |

### 3.2 Test Device

| Item | Details |
|---|---|
| Device | Google Pixel 6 Pro |
| Screen Size | 6.7 inches |
| Resolution | 1440 x 3120 pixels |
| Density | 560 dpi (xxxhdpi) |
| Android Version | API 33/34 (Android 13/14) |

### 3.3 Dependencies

| Library | Purpose |
|---|---|
| Jetpack Compose | Declarative UI framework |
| Navigation Compose | Screen routing and back stack |
| ViewModel + StateFlow | State management |
| DataStore Preferences | Local persistent storage |
| Material 3 | UI components and theming |
| Material Icons Extended | Icon set |
| Gson | JSON serialization |
| Androidx Core KTX | Kotlin Android extensions |

---

## 4. Constraints

- iOS is **not supported** — Android only
- Full Bible verse text is partially implemented (key chapters only)
- Translation switching is UI-only — full multi-translation data not yet included
- Internet connection is not required and not used
- Publishing to Google Play Store requires a one-time **$25 developer fee**

---

## 5. Future Requirements *(Nice to Have)*

- [ ] Full Bible text for all 66 books and all chapters
- [ ] Multiple Bible translations with actual verse data
- [ ] Audio Bible playback
- [ ] Daily reading plan / devotional
- [ ] Cloud sync for bookmarks and notes
- [ ] Cross-platform support (iOS via Flutter or React Native)
- [ ] Share verse as image to social media
- [ ] Offline maps of Bible lands

---

*Mobile Bible App — Personal / Educational Use*
