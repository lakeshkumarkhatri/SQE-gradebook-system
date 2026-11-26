# Selenium Explicit Waits Notes — GradeBook UI

## `time.sleep` vs. Explicit Waits (`WebDriverWait`)
- **Fixed `time.sleep(0.2)`**: Waits only 0.2s after clicking Add Score while the `POST /students/<id>/scores` proxy has a simulated 1.0s delay. Because the async update has not completed yet, the table still shows `'0.00'`.
- **Explicit Waits (`WebDriverWait` + `expected_conditions`)**: Polls the DOM (`text_to_be_present_in_element` on `#avg-<id>`) until the updated average `'80.00'` appears or the timeout expires.

## 5-Run Latency Experiment Results (1.0s Delay on `POST /students/<id>/scores`)

| Run | `time.sleep(0.2)` | `WebDriverWait` (timeout=5s) |
| --- | --- | --- |
| Run 1 | FAIL (`AssertionError: Expected '80.00', got '0.00'`) | PASS |
| Run 2 | FAIL (`AssertionError: Expected '80.00', got '0.00'`) | PASS |
| Run 3 | FAIL (`AssertionError: Expected '80.00', got '0.00'`) | PASS |
| Run 4 | FAIL (`AssertionError: Expected '80.00', got '0.00'`) | PASS |
| Run 5 | FAIL (`AssertionError: Expected '80.00', got '0.00'`) | PASS |

## Conditions Used in `StudentPage`
- `presence_of_element_located`: Ensures `#students-table`, `#row-<id>`, or `#avg-<id>` exists in the DOM.
- `element_to_be_clickable`: Ensures inputs/buttons (`#name-input`, `#add-btn`, `#score-input-<id>`, `#score-btn-<id>`) are present and interactive before typing or clicking.
- `visibility_of_element_located`: Ensures `#validation-error` is visible before reading validation messages.
- `text_to_be_present_in_element`: Waits until an asynchronous score update reflects the expected average text in `#avg-<id>`.

## Experiment Takeaways
- Short fixed sleeps cause deterministic or flaky assertion failures whenever backend latency exceeds the sleep duration (`0.2s < 1.0s`), while long fixed sleeps slow down every test even when responses are fast.
- Explicit waits adapt dynamically to actual UI state changes across Flask (`/api/...`) and Spring Boot (`:8080`).