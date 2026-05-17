# SCR-002 — Search Screen

---

## Screen Info

| Field | Details |
|---|---|
| **Screen ID** | SCR-002 |
| **Screen Name** | Search Screen |
| **Route** | `/search` |
| **Accessed From** | Bottom Navigation — Search Tab |
| **Description** | Full-text search screen. Allows users to search Bible verses by keyword, filter by testament or type, and manage recent searches. |

---

## Screen Annotations

1. **Status Bar** — Displays time (9:41) and signal/battery indicators. Placeholder only.
2. **Screen Title** — Large bold "Search" label at the top identifying the current screen.
3. **Search Input Field** — Primary text input with a search icon on the left, a clear (X) button on the right, and a filter icon on the far right.
4. **Filter Tabs** — Horizontal tab row below the input: All, OT, NT, Verse, Topic. Used to narrow the search scope.
5. **Active Filter Chips** — Removable pill-shaped filter tags displayed below the tabs showing currently active filters.
6. **Search Results List** — Scrollable list of verse results. Each row shows a book icon, verse reference, verse text preview, and a bookmark icon.
7. **Bottom Navigation Bar** — Persistent 5-tab navigation bar. Search tab is active.

---

## Component Legend

| ID | Type | Label | Function |
|---|---|---|---|
| TXT-01 | Text | Screen Title | Large label identifying the screen as "Search" |
| INP-01 | Input Field | Search Bar | Primary text input — accepts keyword search query from user |
| ICN-01 | Icon | Search Icon | Magnifying glass icon inside the input field on the left |
| BTN-01 | Button | Clear (X) Button | Clears the current text inside the search input field |
| ICN-02 | Icon | Filter Icon | Opens advanced filter panel or options on the right of input |
| TAB-01 | Tab | All | Displays all search results across both testaments |
| TAB-02 | Tab | OT | Filters search results to Old Testament books only |
| TAB-03 | Tab | NT | Filters search results to New Testament books only |
| TAB-04 | Tab | Verse | Filters results to match by verse keyword |
| TAB-05 | Tab | Topic | Filters results by topical category |
| CHP-01 | Chip | Active Filter Tag 1 | Removable pill tag showing a currently active filter |
| CHP-02 | Chip | Active Filter Tag 2 | Removable pill tag showing a secondary active filter |
| LST-01 | List | Search Results List | Scrollable list of Bible verse results matching the query |
| ICN-03 | Icon | Book Icon | Small icon on the left of each result row |
| TXT-02 | Text | Verse Reference | Shows book name, chapter, and verse per result row |
| TXT-03 | Text | Verse Preview | Short snippet of the verse text in each result row |
| BTN-02 | Button | Bookmark Icon | Tapping bookmarks the verse in that result row |
| NAV-01 | Nav Item | Home | Bottom nav — navigates to SCR-001 Home Screen |
| NAV-02 | Nav Item | Search | Bottom nav — current screen (active state) |
| NAV-03 | Nav Item | Books | Bottom nav — navigates to SCR-003 Books Screen |
| NAV-04 | Nav Item | Bookmarks | Bottom nav — navigates to SCR-005 Bookmarks Screen |
| NAV-05 | Nav Item | Settings | Bottom nav — navigates to SCR-006 Settings Screen |

---

## Navigation From This Screen

| Destination | Trigger |
|---|---|
| SCR-004 Reader | Tap a verse result row in the search results list |
| SCR-001 Home | Tap Home icon in bottom navigation bar |
| SCR-003 Books | Tap Books icon in bottom navigation bar |
| SCR-005 Bookmarks | Tap Bookmarks icon in bottom navigation bar |
| SCR-006 Settings | Tap Settings icon in bottom navigation bar |

---

*Mobile Bible App — Screen Specifications v1.0 — May 2026*
