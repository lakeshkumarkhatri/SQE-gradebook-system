
# Lab 7 — Fixture Refactor

## Function Scope
JUnit `@BeforeEach` creates a fresh student before every test in
`ValidateNameTest`. This keeps test setup independent.

## Class Scope
JUnit `@BeforeAll` runs once before all tests in
`GradebookLetterGradeTest`. It is suitable for shared, one-time setup.

## Adaptation
The lab handout uses Python/pytest fixtures. This Java project uses
JUnit 5 lifecycle annotations to demonstrate the corresponding concepts.
