# Lab 7 - Unit Testing Notes

## 1. Test Framework

This project uses Java 21 and JUnit 5. The lab handout uses Python and pytest, so the requirements are adapted to Java where possible.

## 2. Running Tests

Run the complete test suite:

    java -jar lib\junit-platform-console-standalone-1.14.1.jar execute --class-path "tests;src\gradebook" --scan-class-path

Latest verified result:
- Tests found: 55
- Tests successful: 55
- Tests failed: 0

## 3. Detailed vs. Concise Output

Detailed output lists individual tests and their results. Use it to identify failures. Concise output emphasizes the overall result and failure details.

JUnit reporting differs from pytest -v and --tb=short. Those pytest commands have not been run in this Java-only workflow.

## 4. Fixture Scope Adaptation

- JUnit @BeforeEach runs before every test method and provides function-level setup.
- JUnit @BeforeAll runs once before test methods in a class and provides class-level setup.
- JUnit has no direct equivalent to pytest's module-scoped fixture. Class-level setup is the adaptation used here.

## 5. File I/O Testing

Roster.save_to_file() uses a writer factory so tests can supply an in-memory writer without creating a real file. The success test checks the written student count and class average. The failure test simulates an IOException and verifies GradeBookIOError.

## 6. Class Average

Roster.class_average() returns 0.0 for an empty roster. Otherwise, it averages the individual students' averages. Tests cover empty, single-student, and multiple-student rosters.

## 7. Parametrized Score Validation

AddScoreTest checks six valid and six invalid score values using JUnit parameterized tests. Invalid cases include negative values, NaN, and infinity.

## 8. CI Configuration Limitation

The repository currently uses Java/JUnit. A pytest.ini file can configure future Python tests, but it does not configure JUnit or make Java tests runnable through pytest.