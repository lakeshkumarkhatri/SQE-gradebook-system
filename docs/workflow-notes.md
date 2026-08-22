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
```

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

## Task 4 — Commit Hygiene Audit

### Recent Commit History

The following is the output of `git log --oneline -10` on the main branch:

```text
79881bf (HEAD -> main, origin/main, origin/HEAD) refactor(gradebook): rename roll number parameter to id number (#7)
f7a5054 refactor(gradebook): rename roll number parameter (#5)
f6e8f71 feat(gradebook): add student score capability
6ef0877 REPO Rename
1481397 syntax issue resolved in workflow-notes.md
83bcf96 syntax issue resolved in workflow-notes.md
50d0f15 docs: add development workflow notes
cc26b3b fix: correct issue template metadata
28ab166 fix: configure issue template metadata
97332af chore: add issue and pull request templates

### Weak Commit Messages and Improved Versions

#### 1. Original
`6ef0877 REPO Rename`

Improved:
`chore(repo): rename repository`

Reason:
The original message is vague and does not follow Conventional Commits. The improved message uses the `chore` type for repository maintenance and clearly describes the change.

#### 2. Original
`1481397 syntax issue resolved in workflow-notes.md`

Improved:
`fix(docs): resolve syntax issue in workflow notes`

Reason:
The original message does not follow Conventional Commits and uses an informal description. The improved message uses the `fix` type, identifies documentation as the affected area, and clearly describes what was fixed.