# Code Review Retrospective

## 1. What Went Well

- The code review checklist provided a structured way to evaluate correctness, testing, readability, design, and risk.
- The reviewer identified important problems in the initial percentile implementation.
- GitHub inline comments and suggestion blocks made feedback specific and actionable.
- Accepting the suggested parameter names improved code readability.
- The updated implementation was tested successfully, with all 69 tests passing.

## 2. What Could Be Improved

- The initial implementation used integer division, which produced incorrect percentile results.
- Empty and single-student rosters were not handled properly.
- The initial implementation lacked method-level JavaDoc.
- Dedicated tests should have been written alongside the initial implementation rather than added after review.

## 3. Lessons Learned

- Code review helps identify defects that developers may overlook when writing their own code.
- Clear variable and parameter names make code easier to understand and maintain.
- Edge cases, such as empty collections, single elements, and equal values, must be considered.
- Unit tests provide confidence that changes work correctly and do not introduce regressions.
- GitHub review features, including inline comments, suggestion blocks, and approval requests, support collaborative software development.

## 4. Conclusion

This exercise demonstrated the importance of structured code reviews in improving software quality. The feedback process helped identify and correct implementation issues, improve documentation, and add tests for important edge cases. The final implementation was successfully reviewed, approved, and merged into the main branch.