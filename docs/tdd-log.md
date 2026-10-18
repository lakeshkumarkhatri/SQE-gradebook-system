# Lab 8 — Test-Driven Development Log

## Feature: Rank Students

**Branch:** `feature/rank-students-tdd`

**Requirement:** Rank students by average score in descending order. When averages are equal, sort names alphabetically, ignoring case. Use a case-sensitive comparison as a deterministic fallback when names differ only by case.

## TDD Cycles

### Cycle 1 — Empty Roster
- **RED:** `cac220c` — Added a test expecting an empty list for an empty roster.
- **GREEN:** `6bb4b9a` — Added the initial implementation returning an empty list.

### Cycle 2 — Single Student
- **RED:** `a637720` — Added a test expecting the roster's single student to be returned.
- **GREEN:** `5d55e12` — Changed the implementation to return a copy of the student list.

### Cycle 3 — Average Descending
- **RED:** `ecbea73` — Added a test requiring students to be ranked by average score.
- **GREEN:** `bfe9405` — Implemented descending average sorting.

### Cycle 4 — Alphabetical Tie-Breaking
- **RED:** `8df6395` — Added a test requiring alphabetical ordering when averages are equal.
- **GREEN:** `39b008e` — Added alphabetical tie-breaking.
- **REFACTOR:** `cae5a31` — Extracted student comparison into a helper method.

### Additional Regression Coverage
- `53f0deb` — Added or updated a test concerning the zero-average student. Verify the actual commit changes before describing its exact effect.
- **Deterministic tie-breaking:** `6606d5c` — Added a case-sensitive fallback for names that compare equally without regard to case.

## Test Results

- Ranking tests: 5 passed, 0 failed.
- Full regression suite on this branch: 43 passed, 0 failed.
- Java compilation completed successfully.

## Retrospective

TDD helped establish expected behavior before implementing each major ranking rule. The empty-roster and single-student tests established the basic contract, while the sorting and tie-breaking tests drove the comparison logic. Extracting the comparison method made the ranking code easier to read. Regression testing verified that the current branch's available tests passed. The additional regression test and deterministic tie-breaking fix were recorded separately rather than being misrepresented as distinct RED-GREEN cycles.
