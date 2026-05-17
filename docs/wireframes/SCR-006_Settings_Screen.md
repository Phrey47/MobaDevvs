# SCR-006 — Settings Screen

---

## Screen Info

| Field | Details |
|---|---|
| **Screen ID** | SCR-006 |
| **Screen Name** | Settings Screen |
| **Route** | `/settings` |
| **Accessed From** | Bottom Navigation — Settings Tab |
| **Description** | App configuration screen. Contains the app logo section, reading preferences (font, theme, translation), account settings, notifications, and about information. |

---

## Screen Annotations

1. **Status Bar** — Displays time (9:41) and signal/battery indicators. Placeholder only.
2. **Screen Title** — Bold "Settings" label at the top identifying the current screen.
3. **App Logo Section** — Circular app logo with the app name beside it and a right arrow (›). Decorative only, replaces the profile avatar since this is a personal app with no user accounts.
4. **Reading Settings Group** — Grouped card section containing: Font size selector (T icon), Theme/display mode toggle, and Language/translation selector.
5. **Notification Settings** — Card section with: Notifications toggle (bell icon, shown ON).
6. **About Section** — Single row with an info circle icon linking to app version and about information.
7. **Bottom Navigation Bar** — Persistent 5-tab navigation bar. Settings tab is active.

---

## Component Legend

| ID | Type | Label | Function |
|---|---|---|---|
| TXT-01 | Text | Screen Title | Bold label identifying the screen as "Settings" |
| LGO-01 | Logo | App Logo | Circular app logo displayed at the top of settings. Decorative only, no user profile or login required. |
| TXT-02 | Text | App Name | Displays the app name beside the logo |
| ICN-01 | Icon | Font Icon (T) | Visual indicator for the font size setting row |
| TXT-03 | Text | Font Size Label | Labels the font size setting row |
| CTL-01 | Control | Font Size Selector | Control to adjust reading font size (S / M / L / XL) |
| ICN-02 | Icon | Theme / Display Icon | Visual indicator for the theme/display mode setting row |
| TXT-04 | Text | Theme Label | Labels the theme or display mode setting row |
| TGL-01 | Toggle | Theme Toggle | Switch between Light and Dark reading mode |
| ICN-03 | Icon | Language / Globe Icon | Visual indicator for the Bible translation setting row |
| TXT-05 | Text | Translation Label | Labels the Bible translation selection row |
| CTL-02 | Control | Translation Selector | Selects active Bible translation (KJV / NIV / ESV / NLT) |
| ICN-04 | Icon | Bell Icon | Visual indicator for the notifications setting row |
| TXT-06 | Text | Notifications Label | Labels the notifications on/off setting row |
| TGL-02 | Toggle | Notifications Toggle | Turns push notifications ON or OFF (wireframe shows ON) |
| ICN-05 | Icon | Info Circle Icon | Visual indicator for the About app row |
| TXT-07 | Text | About Label | Labels the about or app information row |
| BTN-01 | Button | About Row | Tappable row that opens app version and info page |
| NAV-01 | Nav Item | Home | Bottom nav — navigates to SCR-001 Home Screen |
| NAV-02 | Nav Item | Search | Bottom nav — navigates to SCR-002 Search Screen |
| NAV-03 | Nav Item | Books | Bottom nav — navigates to SCR-003 Books Screen |
| NAV-04 | Nav Item | Bookmarks | Bottom nav — navigates to SCR-005 Bookmarks Screen |
| NAV-05 | Nav Item | Settings | Bottom nav — current screen (active state) |

---

## Navigation From This Screen

| Destination | Trigger |
|---|---|
| SCR-001 Home | Tap Home icon in bottom navigation bar |
| SCR-002 Search | Tap Search icon in bottom navigation bar |
| SCR-003 Books | Tap Books icon in bottom navigation bar |
| SCR-005 Bookmarks | Tap Bookmarks icon in bottom navigation bar |

---

*Mobile Bible App — Screen Specifications v1.0 — May 2026*
