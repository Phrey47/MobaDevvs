# SCR-005 — Bookmarks Screen

---

## Screen Info

| Field | Details |
|---|---|
| **Screen ID** | SCR-005 |
| **Screen Name** | Bookmarks Screen |
| **Route** | `/bookmarks` |
| **Accessed From** | Bottom Navigation — Bookmarks Tab |
| **Description** | Displays all saved bookmarks and notes. Each item shows the verse reference, text preview, highlight color strip, and note indicator. Tapping an item navigates to the Reader. |

---

## Screen Annotations

1. **Status Bar** — Displays time (9:41) and signal/battery indicators. Placeholder only.
2. **Screen Title** — Bold "Bookmarks" label at the top identifying the current screen.
3. **Filter / Sort Row** — Horizontal row with sort and filter options and a more options (…) button on the right.
4. **Bookmark List** — Scrollable list of all saved bookmarks. Each item shows: bookmark icon, verse reference, verse preview text, and a more options (…) menu.
5. **Note Indicators** — Small note icon displayed below some bookmark items to indicate an attached personal note exists.
6. **Highlight Color Strip** — Vertical colored strip on the left edge of bookmark items showing the highlight color applied to that verse (yellow, green).
7. **Bottom Navigation Bar** — Persistent 5-tab navigation bar. Bookmarks tab is active.

---

## Component Legend

| ID | Type | Label | Function |
|---|---|---|---|
| TXT-01 | Text | Screen Title | Bold label identifying the screen as "Bookmarks" |
| TAB-01 | Tab | Filter / Sort Options | Horizontal tabs for filtering or sorting the bookmark list |
| BTN-01 | Button | More Options (…) | Opens additional sort or filter action options |
| LST-01 | List | Bookmark List | Scrollable list of all saved bookmarked verses |
| ICN-01 | Icon | Bookmark Icon | Filled bookmark icon indicating the item is saved |
| TXT-02 | Text | Verse Reference | Shows book name, chapter, and verse of the bookmark |
| TXT-03 | Text | Verse Preview | Short preview snippet of the bookmarked verse text |
| BTN-02 | Button | Item More (…) | Opens per-item options: edit note, delete, share |
| ICN-02 | Icon | Note Indicator | Small icon showing a personal note is attached to the item |
| HLT-01 | Highlight | Highlight Color Strip | Left-side vertical color strip showing applied highlight color |
| NAV-01 | Nav Item | Home | Bottom nav — navigates to SCR-001 Home Screen |
| NAV-02 | Nav Item | Search | Bottom nav — navigates to SCR-002 Search Screen |
| NAV-03 | Nav Item | Books | Bottom nav — navigates to SCR-003 Books Screen |
| NAV-04 | Nav Item | Bookmarks | Bottom nav — current screen (active state) |
| NAV-05 | Nav Item | Settings | Bottom nav — navigates to SCR-006 Settings Screen |

---

## Navigation From This Screen

| Destination | Trigger |
|---|---|
| SCR-004 Reader | Tap any bookmark item to open its source chapter |
| SCR-001 Home | Tap Home icon in bottom navigation bar |
| SCR-002 Search | Tap Search icon in bottom navigation bar |
| SCR-003 Books | Tap Books icon in bottom navigation bar |
| SCR-006 Settings | Tap Settings icon in bottom navigation bar |

---

*Mobile Bible App — Screen Specifications v1.0 — May 2026*
