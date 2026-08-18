# SQE Development Workflow

## Workflow Diagram

```text
Idea
  ↓
Issue
  ↓
Branch
  ↓
Pull Request
  ↓
Review
  ↓
Merge
  ↓
CI
  ↓
Release

## QA Engineer Involvement

### 1. Idea
A QA engineer helps identify quality risks, testability concerns, and acceptance criteria before implementation begins.

### 2. Issue
QA reviews the issue requirements and acceptance criteria to ensure they are clear, complete, and testable.

### 3. Branch
QA considers the planned change and identifies the types of testing needed for the feature or fix.

### 4. Pull Request
QA reviews the proposed changes and checks whether appropriate tests and documentation have been included.

### 5. Review
QA identifies defects, missing test cases, edge cases, and potential quality risks during review.

### 6. Merge
QA verifies that required reviews and checks have passed before the change is merged into the main branch.

### 7. CI
QA uses automated CI checks to verify that tests pass and that the change does not introduce regressions.

### 8. Release
QA validates the final build and confirms that the release meets the required quality and acceptance criteria.