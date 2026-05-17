# Mobile Bible App — Requirements Documentation

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

- The app shall display all 66 books of the Bible (39 Old Testament, 27 New Testament)
- The app shall allow users to navigate books, chapters, and verses
- The app shall support chapter-by-chapter reading with previous and next navigation
- The app shall display verse numbers alongside verse text
- The app shall remember the last chapter read and allow users to continue reading

---

### 1.2 Verse Interactions

- The app shall allow users to tap a verse to select it
- The app shall allow users to bookmark selected verses
- The app shall allow users to highlight selected verses in 4 colors: Yellow, Green, Blue, and Pink
- The app shall allow users to add personal notes to any selected verse
- The app shall allow users to copy verse text to the device clipboard
- The app shall allow users to remove bookmarks, highlights, and notes

---

### 1.3 Home Screen

- The app shall display a daily verse that changes every day
- The app shall display a reading streak counter showing the user's consecutive reading days
- The app shall display quick access to popular Bible books via a 2-row grid
- The app shall display the last read chapter for quick resume
- The app shall display recent reading history showing the last 5–10 chapters read

---

### 1.4 Search

- The app shall provide full-text search across all stored Bible verses
- The app shall highlight the search term inside results
- The app shall display search suggestions when the search bar is empty
- The app shall save and display recent search history
- The app shall allow users to clear recent search history
- The app shall allow filtering of results by: All, Old Testament, New Testament, Verse, and Topic

---

### 1.5 Saved Items

- The app shall display all bookmarked verses in a dedicated Bookmarks tab
- The app shall display all notes in a dedicated Notes tab
- The app shall show a highlight color strip on each saved item indicating the applied highlight color
- The app shall show a note indicator on bookmark items that have an attached personal note
- The app shall allow navigation to the source chapter from any saved item
- The app shall allow deletion of individual bookmarks and notes via a per-item options menu

---

### 1.6 Settings

- The app shall allow users to switch between Light, Dark, and Sepia reading themes
- The app shall allow users to adjust font size: Small, Medium, Large, and Extra Large
- The app shall allow users to switch Bible translations: KJV, NIV, ESV, and NLT
- The app shall allow users to toggle push notifications ON or OFF
- The app shall display the app logo in Settings. No user profile or login is required — this is a personal app

---

## 2. Non-Functional Requirements

### 2.1 Performance

- The app shall launch within 3 seconds on supported devices
- Screen transitions shall be smooth with no visible lag
- Search results shall appear within 1 second of input

---

### 2.2 Usability

- The app shall follow Material Design 3 guidelines
- All screens shall be accessible via the bottom navigation bar (NAV-01 to NAV-05)
- The app shall be usable with one hand on a standard smartphone

---

### 2.3 Compatibility

- The app shall support Android 5.0 and above (API 21+)
- The app shall support screen densities from mdpi to xxxhdpi
- The app shall support portrait orientation

---

### 2.4 Data & Storage

- All user data — bookmarks, highlights, notes, settings — shall be stored locally on the device
- User data shall persist between app sessions and restarts
- No internet connection shall be required to use the app

---

### 2.5 Security & Privacy

- The app shall not collect or transmit any user data
- All data shall remain on the user's device only
- No login or account creation shall be required — this is a personal Bible app

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

---

### 3.2 Test Device

| Item | Details |
|---|---|
| Device | Google Pixel 6 Pro |
| Screen Size | 6.7 inches |
| Resolution | 1440 x 3120 pixels |
| Density | 560 dpi (xxxhdpi) |
| Android Version | API 33/34 (Android 13/14) |

---

### 3.3 Dependencies

| Library | Purpose |
|---|---|
| Jetpack Compose | Declarative UI framework |
| Navigation Compose | Screen routing and back stack management |
| ViewModel + StateFlow | State management across screens |
| DataStore Preferences | Local persistent storage for user data |
| Material 3 + Icons Extended | UI components, theming, and icon set |
| Gson | JSON serialization for stored data |
| Androidx Core KTX | Kotlin Android extensions |

---

## 4. Constraints

- iOS is not supported — Android only
- Full Bible verse text is partially implemented for key chapters only
- Translation switching (KJV/NIV/ESV/NLT) is UI-only — full multi-translation verse data not yet included
- Internet connection is not required and not used
- Publishing to Google Play Store requires a one-time USD 25 Google Developer account registration fee
- No user login or account system — all data is stored locally per device

---

## 5. Future Requirements

> Nice to have — not in current scope

- [ ] Full Bible text for all 66 books and all chapters
- [ ] Multiple Bible translations with actual verse data (NIV, ESV, NLT)
- [ ] Audio Bible playback
- [ ] Daily reading plan and devotional
- [ ] Cloud sync for bookmarks, highlights, and notes
- [ ] Cross-platform support — iOS via Flutter or React Native
- [ ] Share verse as image to social media
- [ ] Offline maps of Bible lands
- [ ] Offline downloadable Bible content packs per translation

---

*Mobile Bible App — Requirements Documentation | May 2026*
