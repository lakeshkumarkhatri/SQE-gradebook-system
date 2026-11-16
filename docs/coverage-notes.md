# Coverage Notes - Lab 11

## Task 1: Baseline Report
- **Date:** November 16, 2026
- **Tool:** JaCoCo / Maven (`jacoco-maven-plugin`)
- **Baseline Line Coverage:** 80% (60 / 75 lines covered)
- **Baseline Branch Coverage:** 88% (39 / 44 branches covered)
- **Baseline Instruction Coverage:** 76% (233 / 306 instructions covered)
- **HTML Report Location:** `target/site/jacoco/index.html`

## Task 2: Identify the Weakest Files & Components
From the baseline HTML report summary, the weakest components were:
1. **`gradebook` package / classes:** 0% line and instruction coverage (12 missed lines, 12 missed methods, 2 classes completely unexercised by tests).
2. **`default` package classes:** 94% instruction coverage and 88% branch coverage, with 3 missed lines and 5 missed/partial branches.

## Task 3: Closing Coverage Gaps
- **Action Taken:** Created and integrated `tests/GradebookClassTest.java` to thoroughly test all unexercised methods, constructors, boundary validation checks (`validateName`, `letterGrade`), score additions, and duplicate roll-number exception branches in `gradebook.java`.
- **Final Result:** Successfully raised code coverage across the entire project to **100% Instruction Coverage** and **100% Branch Coverage** (0 missed lines/branches out of 246 instructions and 44 branches).

## Task 4: Coverage vs. Quality Discussion
- **The Coverage Trap:** A test that merely invokes a method like `letterGrade(75.0)` or `addScore(85.0)` without strict assertions will achieve high line coverage quantity, but provides zero proof that the logic correctly calculates the expected output or handles edge cases properly. 
- **The Solution:** We strengthened our assertions across `GradebookClassTest.java` to explicitly verify exact return values (e.g., verifying that score averages match expected calculations and letter grades match exact boundaries like `"C"` or `"A"`), ensuring that code quantity aligns directly with robust software quality assurance.

## Task 5: Enforced Threshold Configuration
- **Threshold Rule:** Configured minimum coverage rules to prevent future test regressions and prepare for upcoming CI pipelines.