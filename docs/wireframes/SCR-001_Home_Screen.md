# SCR-001 — Home Screen

---

## Screen Info

| Field | Details |
|---|---|
| **Screen ID** | SCR-001 |
| **Screen Name** | Home Screen |
| **Route** | `/home` |
| **Accessed From** | Bottom Navigation — Home Tab (all screens) |
| **Description** | Main landing screen. Shows daily verse, continue reading widget, and quick-access book grid. |

---

## Screen Annotations

1. **Status Bar** — Displays time (9:41) and signal/battery indicators. Placeholder only, not interactive.
2. **Greeting Header** — Displays a time-based greeting text on the left and the app logo on the top-right corner. Decorative only.
3. **Daily Verse Card** — Large prominent card showing an auto-loaded verse of the day. Includes verse text and reference label.
4. **Continue Reading Widget** — Displays the last read book title and chapter with a play/resume button to continue reading from last position.
5. **Quick Access Book Grid** — A 2-row grid of recently visited or popular Bible books displayed as thumbnail cards.
6. **Bottom Navigation Bar** — Persistent 5-tab navigation bar fixed at the bottom. Tabs: Home (active), Search, Books, Bookmarks, Settings.

---

## Component Legend

| ID | Type | Label | Function |
|---|---|---|---|
| HDR-01 | Header | Greeting Text | Displays time-based greeting to the user (e.g. Good Morning) |
| LGO-01 | Logo | App Logo | Circular app logo on the top-right of the Home Screen. Decorative only, not interactive. |
| CRD-01 | Card | Daily Verse Card | Displays the auto-loaded verse of the day with verse reference |
| TXT-01 | Text | Verse Text | Main verse content text inside the daily verse card |
| TXT-02 | Text | Verse Reference | Shows book name, chapter, and verse number (e.g. John 3:16) |
| WGT-01 | Widget | Continue Reading | Shows last read book and chapter with a resume action button |
| BTN-01 | Button | Resume / Play Button | Tapping resumes reading from the last saved position |
| GRD-01 | List | Quick Access Book Grid | 2-row grid layout showing popular or recently visited books |
| ICN-01 | Icon | Book Thumbnail | Square image or placeholder icon for each book in the grid |
| NAV-01 | Nav Item | Home | Bottom nav — navigates to SCR-001 (currently active) |
| NAV-02 | Nav Item | Search | Bottom nav — navigates to SCR-002 Search Screen |
| NAV-03 | Nav Item | Books | Bottom nav — navigates to SCR-003 Books Screen |
| NAV-04 | Nav Item | Bookmarks | Bottom nav — navigates to SCR-005 Bookmarks Screen |
| NAV-05 | Nav Item | Settings | Bottom nav — navigates to SCR-006 Settings Screen |

---

## Navigation From This Screen

| Destination | Trigger |
|---|---|
| SCR-002 Search | Tap Search icon in bottom navigation bar |
| SCR-003 Books | Tap Books icon in bottom navigation bar or Quick Access Grid card |
| SCR-004 Reader | Tap Continue Reading widget or book card in grid |
| SCR-005 Bookmarks | Tap Bookmarks icon in bottom navigation bar |
| SCR-006 Settings | Tap Settings icon in bottom navigation bar |

---

*Mobile Bible App — Screen Specifications v1.0 — May 2026*
