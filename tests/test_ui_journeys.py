import json
import os
import time
import unittest
from selenium import webdriver
from selenium.common.exceptions import StaleElementReferenceException
from selenium.webdriver.chrome.options import Options

from pages.student_page import StudentPage

BASE_URL = os.getenv("GRADEBOOK_UI_URL", "http://127.0.0.1:5000")


def build_driver(headless=True):
    options = Options()
    if headless:
        options.add_argument("--headless=new")
    options.add_argument("--no-sandbox")
    options.add_argument("--disable-dev-shm-usage")
    options.set_capability("goog:loggingPrefs", {"performance": "ALL"})
    return webdriver.Chrome(options=options)


def _next_student_id(counter=[0]):
    counter[0] += 1
    suffix = (int(time.time() * 100) + counter[0]) % 9000
    return f"S{1000 + suffix}"


class GradeBookUiJourneysTest(unittest.TestCase):
    @classmethod
    def setUpClass(cls):
        headless = os.getenv("HEADLESS", "1").lower() not in ("0", "false", "no")
        cls.driver = build_driver(headless=headless)
        cls.driver.implicitly_wait(0)
        cls.page = StudentPage(cls.driver, timeout=5)

    @classmethod
    def tearDownClass(cls):
        if hasattr(cls, "driver"):
            cls.driver.quit()

    def setUp(self):
        self.page.open(BASE_URL)
        # Drain any prior Chrome performance logs before starting the test
        self.driver.get_log("performance")

    def _count_student_create_requests(self):
        count = 0
        for entry in self.driver.get_log("performance"):
            msg = json.loads(entry["message"]).get("message", {})
            if msg.get("method") == "Network.requestWillBeSent":
                req = msg.get("params", {}).get("request", {})
                url = req.get("url", "")
                if req.get("method") == "POST" and "/api/students" in url and not url.endswith("/scores"):
                    count += 1
        return count

    def _wait_for_avg_change(self, sid, old_avg):
        def _changed(driver):
            try:
                err = driver.find_element("id", "validation-error").text.strip()
                if err:
                    raise AssertionError(f"Unexpected validation error while adding score: {err}")
                text = driver.find_element("id", f"avg-{sid}").text.strip()
                return text if (text and text != old_avg) else False
            except StaleElementReferenceException:
                return False

        return self.page.wait.until(_changed)

    def test_1_add_student_appears_in_table(self):
        sid = _next_student_id()
        self.page.add_student("Sana Tariq", sid)
        row_text = self.page.student_row_text(sid)
        self.assertIn(sid, row_text)
        self.assertIn("Sana Tariq", row_text)

    def test_2_add_scores_updates_average(self):
        sid = _next_student_id()
        self.page.add_student("Bilal Ahmed", sid)
        self.page.student_row_text(sid)

        self.page.add_score(sid, 80)
        self.page.wait_for_average(sid, "80.00")

        self.page.add_score(sid, 90)
        avg_text = self.page.wait_for_average(sid, "85.00")
        self.assertIn("85.00", avg_text)

    def test_3_empty_name_shows_error_without_calling_api(self):
        sid = _next_student_id()
        self.page.add_student("", sid)
        err_text = self.page.get_validation_error()
        self.assertTrue(len(err_text) > 0)
        self.assertEqual(self._count_student_create_requests(), 0)

    def test_4_extra_score_blocked_after_score_limit(self):
        sid = _next_student_id()
        self.page.add_student("Zoya Memon", sid)
        self.page.student_row_text(sid)

        current_avg = self.page.get_average_display(sid)
        for score in [70, 75, 80, 85, 90]:
            self.page.add_score(sid, score)
            current_avg = self._wait_for_avg_change(sid, current_avg)

        # Exceeding the backend's 5-score cap should surface a validation error
        self.page.add_score(sid, 95)
        err_text = self.page.get_validation_error()
        self.assertIn("limit of 5 scores reached", err_text)


if __name__ == "__main__":
    unittest.main(verbosity=2)
    