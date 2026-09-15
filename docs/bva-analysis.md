# Boundary Value Analysis — GradeBook

## Task 1 — Enumerate All Boundaries

Boundary Value Analysis (BVA) focuses on values at and immediately around
the boundaries where the expected system behaviour changes.

For `letterGrade()`, the valid score domain is 0–100, with grade cut-offs
at 60, 70, 80, and 90.

### Boundary Table

| Boundary | Value-1 | Value | Value+1 | Expected Result |
|---|---:|---:|---:|---|
| 0 — Minimum domain edge | -1 | 0 | 1 | -1 → Invalid, 0 → F, 1 → F |
| 60 — F/D boundary | 59 | 60 | 61 | 59 → F, 60 → D, 61 → D |
| 70 — D/C boundary | 69 | 70 | 71 | 69 → D, 70 → C, 71 → C |
| 80 — C/B boundary | 79 | 80 | 81 | 79 → C, 80 → B, 81 → B |
| 90 — B/A boundary | 89 | 90 | 91 | 89 → B, 90 → A, 91 → A |
| 100 — Maximum domain edge | 99 | 100 | 101 | 99 → A, 100 → A, 101 → Invalid |

### Boundary Values

The complete set of boundary-focused values is:

- `-1` — below minimum, invalid
- `0` — minimum valid score, F
- `1` — just above minimum, F
- `59` — just below D boundary, F
- `60` — lowest D score, D
- `61` — just above D boundary, D
- `69` — just below C boundary, D
- `70` — lowest C score, C
- `71` — just above C boundary, C
- `79` — just below B boundary, C
- `80` — lowest B score, B
- `81` — just above B boundary, B
- `89` — just below A boundary, B
- `90` — lowest A score, A
- `91` — just above A boundary, A
- `99` — just below maximum, A
- `100` — maximum valid score, A
- `101` — above maximum, invalid

### BVA Limitation

Boundary Value Analysis is effective for detecting defects at input
boundaries, especially off-by-one errors. However, it may not detect
defects occurring in the middle of an equivalence class. Therefore, BVA
should be combined with Equivalence Partitioning (EP) from Lab 5 for
broader test coverage.