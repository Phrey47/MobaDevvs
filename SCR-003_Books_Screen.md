# SCR-003 — Books Screen

---

## Screen Info

| Field | Details |
|---|---|
| **Screen ID** | SCR-003 |
| **Screen Name** | Books Screen |
| **Route** | `/books` |
| **Accessed From** | Bottom Navigation — Books Tab \| SCR-001 Quick Access Grid |
| **Description** | Displays all 66 Bible books in a 2-column grid. Filterable by Old Testament or New Testament tabs. Tapping a book navigates to the Reader. |

---

## Screen Annotations

1. **Status Bar** — Displays time (9:41) and signal/battery indicators. Placeholder only.
2. **Screen Title** — Bold "Books" label at the top identifying the current screen.
3. **Search Bar** — Text input field used to filter the book list by name in real time.
4. **Testament Tabs** — Two tabs — Old Testament and New Testament — to filter the book list by canon section.
5. **Book Grid List** — Scrollable 2-column grid of Bible books. Each card shows a book icon, book name, and a right arrow indicator.
6. **Section Dividers** — Visual horizontal separators grouping books by alphabet or category within the list.
7. **Bottom Navigation Bar** — Persistent 5-tab navigation bar. Books tab is active.

---

## Component Legend

| ID | Type | Label | Function |
|---|---|---|---|
| TXT-01 | Text | Screen Title | Bold label identifying the screen as "Books" |
| INP-01 | Input Field | Search Books | Text input that filters the book list by book name in real time |
| TAB-01 | Tab | Old Testament | Filters list to show only the 39 Old Testament books |
| TAB-02 | Tab | New Testament | Filters list to show only the 27 New Testament books |
| LST-01 | List | Book Grid | 2-column scrollable grid displaying all Bible book cards |
| CRD-01 | Card | Book Card | Tappable card for each Bible book — navigates to reader |
| ICN-01 | Icon | Book Icon | Visual icon placeholder representing each book |
| TXT-02 | Text | Book Name | Displays the full name of the Bible book on the card |
| ICN-02 | Icon | Arrow (›) | Right-facing arrow indicating the card is tappable |
| DIV-01 | Divider | Section Label | Horizontal divider grouping books by category or letter |
| NAV-01 | Nav Item | Home | Bottom nav — navigates to SCR-001 Home Screen |
| NAV-02 | Nav Item | Search | Bottom nav — navigates to SCR-002 Search Screen |
| NAV-03 | Nav Item | Books | Bottom nav — current screen (active state) |
| NAV-04 | Nav Item | Bookmarks | Bottom nav — navigates to SCR-005 Bookmarks Screen |
| NAV-05 | Nav Item | Settings | Bottom nav — navigates to SCR-006 Settings Screen |

---

## Navigation From This Screen

| Destination | Trigger |
|---|---|
| SCR-004 Reader | Tap a book card then select a chapter |
| SCR-001 Home | Tap Home icon in bottom navigation bar |
| SCR-002 Search | Tap Search icon in bottom navigation bar |
| SCR-005 Bookmarks | Tap Bookmarks icon in bottom navigation bar |
| SCR-006 Settings | Tap Settings icon in bottom navigation bar |

---

*Mobile Bible App — Screen Specifications v1.0 — May 2026*
