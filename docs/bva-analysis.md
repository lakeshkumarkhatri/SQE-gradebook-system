# Boundary Value Analysis

## 1. `letterGrade()` Boundaries

| Boundary | Value - 1 | Value | Value + 1 | Expected Result |
|---|---:|---:|---:|---|
| Minimum domain | -1 | 0 | 1 | -1: Invalid, 0: F, 1: F |
| F/D boundary | 59 | 60 | 61 | 59: F, 60: D, 61: D |
| D/C boundary | 69 | 70 | 71 | 69: D, 70: C, 71: C |
| C/B boundary | 79 | 80 | 81 | 79: C, 80: B, 81: B |
| B/A boundary | 89 | 90 | 91 | 89: B, 90: A, 91: A |
| Maximum domain | 99 | 100 | 101 | 99: A, 100: A, 101: Invalid |

### Boundary Test Result

All required `letterGrade()` boundary cases passed.

- 18 tests executed
- 18 tests successful
- 0 tests failed

---

## 2. `Roster` Score-Count Boundaries

Business rule: a student must have between **1 and 6 scores**.

| Boundary | Value - 1 | Value | Value + 1 | Expected Result |
|---|---:|---:|---:|---|
| Minimum valid score count | 0 | 1 | 2 | 0: Invalid, 1: Valid, 2: Valid |
| Maximum valid score count | 5 | 6 | 7 | 5: Valid, 6: Valid, 7: Invalid |

### Boundary Test Result

All required score-count boundary cases passed.

- 6 tests executed
- 6 tests successful
- 0 tests failed

No defect was found in the `Roster` score-count boundary behavior.

---

## 3. `validateName()` Length Boundaries

Business rule: student name must be non-empty and must not exceed **50 characters**.

| Boundary | Length - 1 | Length | Length + 1 | Expected Result |
|---|---:|---:|---:|---|
| Minimum length | 0 | 1 | 2 | 0: Invalid, 1: Valid, 2: Valid |
| Maximum length | 49 | 50 | 51 | 49: Valid, 50: Valid, 51: Invalid |

The required boundary lengths tested were:

- 0 characters → Invalid
- 1 character → Valid
- 49 characters → Valid
- 50 characters → Valid
- 51 characters → Invalid

### Boundary Test Result

All required name-length boundary cases passed.

- 5 required boundary lengths tested
- All passed
- No boundary defect was found

---

## 4. Defect Tracking

The Lab 6 boundary tests did not reveal an off-by-one defect in the three functions under test.

Existing repository defects were not falsely attributed to Lab 6 boundary testing because they were not discovered by these boundary suites.

No artificial defect was introduced solely to satisfy the defect requirement.

---

## 5. Overall BVA Result

The Boundary Value Analysis suites confirmed the expected behavior at the defined domain and decision boundaries.

| Function | Boundary Tests | Passed | Failed |
|---|---:|---:|---:|
| `letterGrade()` | 18 | 18 | 0 |
| `Roster` score count | 6 | 6 | 0 |
| `validateName()` length | 5 | 5 | 0 |
| **Total** | **29** | **29** | **0** |