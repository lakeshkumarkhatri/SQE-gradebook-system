# GradeBook Test Plan

## 1. Introduction

This Test Plan defines the testing approach for the GradeBook module of the Software Quality Engineering project. The purpose is to verify that student records, score entry, score validation, duplicate roll-number handling, and average calculation behave as expected. Testing will also cover the required negative, boundary, and regression scenarios defined for this lab.

## 2. Test Items

The primary test item is the `gradebook` class located in `src/gradebook/gradebook.java`. The class stores a student's name, roll number, and scores, and provides functionality for score validation, duplicate roll-number detection, and average calculation. The `student.java` file is currently empty and is not treated as an implemented test item.

## 3. Features to be Tested

The following features will be tested:

- Adding valid scores using `addScore()`
- Rejecting negative and invalid score values
- Handling minimum and maximum score boundary values
- Calculating averages with empty, single, and multiple scores
- Rejecting duplicate roll numbers
- Name case-insensitivity as required by the lab
- Grade-letter conversion scenarios required by the lab
- Regression of previously fixed GradeBook defects

## 4. Features Not to be Tested

Graphical user interface testing is out of scope because the current GradeBook project does not contain a graphical user interface. Database integration, network communication, and deployment testing are also outside the scope because these components are not implemented in the current codebase. Performance and security testing beyond the functional scenarios defined for this lab are not included.

## 5. Test Approach

Testing will use functional, negative, boundary, and regression test cases. The test cases will be executed manually against the current GradeBook implementation using the available Java environment. Boundary-value testing will include scores of 0 and 100, while negative testing will verify handling of invalid inputs. Regression testing will verify previously addressed defects involving empty averages, negative scores, and duplicate roll numbers.

## 6. Pass/Fail Criteria

Testing will be considered successful when at least 95% of the planned test cases pass and zero Critical defects remain open. A test case will be marked Pass when the actual result matches the expected result. It will be marked Fail when the expected behavior is not observed and Blocked when execution cannot be completed because of an unavailable prerequisite or system limitation.

## 7. Test Deliverables

The following deliverables will be produced:

- `docs/test-plan.md` containing the overall test plan
- `docs/test-cases.md` containing 12 detailed test cases and execution results
- `docs/rtm.md` containing the requirements-to-test-case traceability matrix
- GitHub defect issues for failed test cases where applicable

## 8. Environmental Needs

Testing will be performed on the local development environment with Java installed and configured. The GradeBook source code under test will be taken from the project's main branch. Java command-line tools and a text editor or IDE may be used to execute and document the tests.

## 9. Schedule

The planned activities follow the three-hour lab schedule:

- Test Plan preparation: 60 minutes
- Test Case preparation: 75 minutes
- Requirements Traceability Matrix: 30 minutes
- Manual test execution: 35 minutes

The activities will be completed sequentially so that requirements and test cases are established before execution.

## 10. Risks

Some required test scenarios may expose functionality that is not currently implemented in the GradeBook code. This may result in failed or blocked test cases during execution. Changes to the implementation during testing could also make the documented results inconsistent, so the tests will be executed against the current code before any corrective changes are made. Incomplete requirement-to-test-case mapping could leave functionality untested, so the RTM will be reviewed for complete coverage.