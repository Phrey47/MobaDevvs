# SCR-004 — Reader Screen

---

## Screen Info

| Field | Details |
|---|---|
| **Screen ID** | SCR-004 |
| **Screen Name** | Reader Screen |
| **Route** | `/reader/{bookId}/{chapter}` |
| **Accessed From** | SCR-003 Books \| SCR-001 Continue Reading \| SCR-001 Quick Access Grid \| SCR-005 Bookmarks |
| **Description** | Primary reading screen. Displays numbered Bible verses for a selected chapter. Supports verse highlighting, bookmarking, chapter navigation, and font/translation settings. |

---

## Screen Annotations

1. **Status Bar** — Displays time (9:41) and signal/battery indicators. Placeholder only.
2. **Top App Bar** — Contains back arrow (←), book/chapter dropdown selectors, a display settings icon, and a share icon on the far right.
3. **Translation Badge** — Small pill-shaped badge showing the active Bible translation (e.g. NIV). Tappable to switch translation.
4. **Chapter Title** — Bold heading displaying the current book name and chapter number.
5. **Verse List** — Numbered scrollable list of all Bible verses for the selected chapter. Verse numbers appear on the left.
6. **Highlighted Verses** — Verses 2 and 5 are shown with a yellow highlight background applied by the user.
7. **Bookmarked Verses** — Bookmark icon visible on the right side of highlighted verses (2 and 5) indicating they are saved.
8. **Chapter Navigation Bar** — Bottom bar with a left arrow (previous chapter), current chapter label in the center, and a right arrow (next chapter).
9. **Bottom Navigation Bar** — Persistent 5-tab navigation. Books tab is active indicating the reader is within the Books section.

---

## Component Legend

| ID | Type | Label | Function |
|---|---|---|---|
| BTN-01 | Button | Back Arrow (←) | Returns the user to the previous screen (SCR-003 Books) |
| DRP-01 | Dropdown | Book Selector | Dropdown to change the current Bible book being read |
| DRP-02 | Dropdown | Chapter Selector | Dropdown to jump directly to a specific chapter number |
| ICN-01 | Icon | Display Settings | Opens font size and display options panel |
| LBL-01 | Badge | Translation Badge | Pill showing active translation (NIV). Tappable to change. |
| TXT-01 | Text | Chapter Title | Bold heading showing current book name and chapter number |
| LST-01 | List | Verse List | Scrollable numbered list of all verses in the chapter |
| TXT-02 | Text | Verse Number | Number label for each verse (1, 2, 3…) on the left side |
| TXT-03 | Text | Verse Text | Full text content of each individual Bible verse |
| ICN-02 | Icon | Bookmark Icon | Filled bookmark icon indicating the verse has been saved |
| BTN-02 | Button | Previous Chapter (←) | Navigates to the previous chapter of the same book |
| LBL-02 | Label | Chapter Label | Displays the current chapter number in the center of nav bar |
| BTN-03 | Button | Next Chapter (→) | Navigates to the next chapter of the same book |
| HLT-01 | Highlight | Yellow Highlight | Yellow background highlight applied to a selected verse row |
| NAV-01 | Nav Item | Home | Bottom nav — navigates to SCR-001 Home Screen |
| NAV-02 | Nav Item | Search | Bottom nav — navigates to SCR-002 Search Screen |
| NAV-03 | Nav Item | Books | Bottom nav — current section (active state) |
| NAV-04 | Nav Item | Bookmarks | Bottom nav — navigates to SCR-005 Bookmarks Screen |
| NAV-05 | Nav Item | Settings | Bottom nav — navigates to SCR-006 Settings Screen |

---

## Navigation From This Screen

| Destination | Trigger |
|---|---|
| SCR-004 Reader (previous chapter) | Tap Previous Chapter (←) button in chapter navigation bar |
| SCR-004 Reader (next chapter) | Tap Next Chapter (→) button in chapter navigation bar |
| SCR-003 Books | Tap Back Arrow (←) in the top app bar |
| SCR-001 Home | Tap Home icon in bottom navigation bar |
| SCR-002 Search | Tap Search icon in bottom navigation bar |
| SCR-005 Bookmarks | Tap Bookmarks icon in bottom navigation bar |
| SCR-006 Settings | Tap Settings icon in bottom navigation bar |

---

*Mobile Bible App — Screen Specifications v1.0 — May 2026*
