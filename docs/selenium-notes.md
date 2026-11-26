# Selenium Explicit Waits Notes — GradeBook UI

## `time.sleep` vs. Explicit Waits (`WebDriverWait`)
- **Fixed `time.sleep(seconds)`**: Pauses execution for a hardcoded duration regardless of actual UI state. Short sleeps cause flaky failures when async API calls or DOM updates take longer; long sleeps waste execution time on every run.
- **Explicit Waits (`WebDriverWait` + `expected_conditions`)**: Polls the DOM until a condition succeeds or the timeout is reached (`TimeoutException`). Tests finish as soon as the UI is ready and fail promptly with clear context if a condition never occurs.

## Conditions Used in `StudentPage`
- `presence_of_element_located`: Ensures `#students-table`, `#row-<id>`, or `#avg-<id>` exists in the DOM.
- `element_to_be_clickable`: Ensures inputs/buttons (`#name-input`, `#add-btn`, `#score-input-<id>`, `#score-btn-<id>`) are present and interactive before typing or clicking.
- `visibility_of_element_located`: Ensures `#validation-error` is actually visible before reading validation messages.
- `text_to_be_present_in_element`: Waits until an asynchronous score update reflects the expected average text in `#avg-<id>`.

## Experiment Takeaways
- Because the UI communicates asynchronously via `/api` (Flask proxy to Spring Boot on port `8080`) and updates table rows without full page reloads, explicit waits prevent race conditions between clicking submit/score buttons and asserting updated row or average values.