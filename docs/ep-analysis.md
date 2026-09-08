# Equivalence Partitioning Analysis

## 1. Number of Scores

Business rule: A student must have between 1 and 6 scores.

| Class | Range | Validity | Representative |
|---|---|---|---|
| EC-S1 | 0 scores | Invalid | 0 |
| EC-S2 | 1–6 scores | Valid | 3 |
| EC-S3 | 7+ scores | Invalid | 8 |

## 2. Student Name

Business rule: The student name must be a non-empty string, have a maximum of 50 characters, and contain only letters, spaces, and hyphens.

| Class | Description | Validity | Representative |
|---|---|---|---|
| EC-N1 | Typical name containing letters and spaces | Valid | `Ali Khan` |
| EC-N2 | Empty string | Invalid | `""` |
| EC-N3 | More than 50 characters | Invalid | 51-character name |
| EC-N4 | Name containing digits | Invalid | `Ali123` |
| EC-N5 | Name containing symbols | Invalid | `Ali@Khan` |

## EP Limitation

Equivalence Partitioning may miss defects at boundaries because it tests representative values from each class. For example, a defect specifically at the boundary between valid and invalid classes may not be detected. Boundary Value Analysis can help identify these boundary-related defects.

## JUnit Test Execution Summary

The full JUnit 5 test suite was executed after implementing the equivalence partitioning tests.

- ValidateNameTest: 5 tests successful
- RosterTest: 3 tests successful
- GradebookLetterGradeTest: 7 tests successful
- Total: 15 tests successful
- Failed: 0
- Skipped: 0

All equivalence partitioning tests passed successfully.