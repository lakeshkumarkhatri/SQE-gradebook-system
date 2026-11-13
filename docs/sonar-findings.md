# SonarCloud Findings

## Analysis Summary

- Project: SQE-gradebook-system
- Analysis Branch: lab-10-sonarcloud-analysis
- Total Issues Found: 1
- Issues Resolved: 1

## Finding 1

| Field | Details |
|---|---|
| Rule | Regular expression performance |
| File | src/gradebook/gradebook.java |
| Line | 73 |
| Severity | Medium |
| Category | Reliability |
| Risk | A regular expression with backtracking may cause excessive processing time for certain inputs. |

### Resolution

Replaced the regular expression validation with a character-by-character loop. The updated implementation checks allowed characters and ensures that the name contains at least one letter.

### Verification

- All 43 JUnit tests passed.
- SonarCloud reported zero reliability issues after the fix.

## Final Status

Only one issue was reported during the analysis. It was resolved and verified. No additional findings were available to document.

## Additional Analysis: Maintainability

The final SonarCloud analysis reported zero open maintainability issues.

No additional code smells were available to fix. Therefore, the requirement to address at least five existing code smells could not be demonstrated because the analysis did not identify five such issues.

## Final Quality Gate Verification

- Quality Gate: PASSED
- New issues: 0
- Coverage on new code: 100%
- Duplications: 0.0%
- Security issues: 0
- Reliability issues: 0
- Maintainability issues: 0
- JUnit tests: 43 passed, 0 failed