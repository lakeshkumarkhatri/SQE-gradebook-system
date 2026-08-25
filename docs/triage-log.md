# Triage Log — v0.2 Grade Statistics

## Fix Order

### 1. Issue #1 — average() returns NaN when no scores are recorded
- Severity: High
- Priority: P1
- Decision: Fix this sprint
- Rationale: This defect causes an invalid result for a core GradeBook calculation and can directly affect grade statistics.

### 2. Issue #3 — Duplicate roll numbers are allowed
- Severity: High
- Priority: P1
- Decision: Fix this sprint
- Rationale: Duplicate roll numbers can make student records ambiguous and lead to incorrect student identification.

### 3. Issue #2 — Negative scores are accepted
- Severity: Medium
- Priority: P1
- Decision: Fix this sprint
- Rationale: The defect allows invalid academic data to enter the GradeBook and can affect later calculations.

### 4. Issue #4 — average() incorrectly truncates decimal values
- Severity: Medium
- Priority: P2
- Decision: Defer this sprint
- Rationale: The rounding defect affects grade accuracy but does not crash the system or allow fundamentally invalid records.

### 5. Issue #5 — Name comparison is case-sensitive
- Severity: Low
- Priority: P2
- Decision: Defer this sprint
- Rationale: The defect mainly affects usability when a student's name is entered with different capitalization and does not corrupt grades or student data.

## Severity and Priority Trade-offs

Issue #2 has Medium severity but P1 priority, while Issue #4 also has Medium severity but P2 priority. Issue #2 is ranked higher because its P1 priority means invalid scores should be addressed sooner, even though its technical severity is only Medium.

Issues #1 and #3 both have High severity and P1 priority. Issue #1 is ranked first because an invalid average directly affects core grade statistics, while Issue #3 primarily affects student identification and may depend on student-management functionality.

## Sprint Decision

Issues #1, #2, and #3 will be fixed during this sprint.

Issues #4 and #5 will not be fixed this sprint because they have lower urgency and can be deferred without blocking the core GradeBook functionality.