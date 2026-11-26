import os
import unittest
from selenium import webdriver
from selenium.webdriver.chrome.options import Options

from pages.student_page import StudentPage

BASE_URL = os.getenv("GRADEBOOK_UI_URL", "http://127.0.0.1:5000")


def build_driver(headless=True):
    options = Options()
    if headless:
        options.add_argument("--headless=new")
    options.add_argument("--no-sandbox")
    options.add_argument("--disable-dev-shm-usage")
    return webdriver.Chrome(options=options)


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

    def test_add_student_appears_in_table(self):
        self.page.add_student("Sana Tariq", "S1003")
        row_text = self.page.student_row_text("S1003")
        self.assertIn("S1003", row_text)
        self.assertIn("Sana Tariq", row_text)

    def test_add_scores_updates_average(self):
        self.page.add_student("Bilal Ahmed", "S1004")
        self.page.student_row_text("S1004")
        self.page.add_score("S1004", 80)
        self.page.wait_for_average("S1004", "80.00")
        self.page.add_score("S1004", 90)
        avg_text = self.page.wait_for_average("S1004", "85.00")
        self.assertIn("85.00", avg_text)

    def test_invalid_student_id_shows_validation_error(self):
        self.page.add_student("Test User", "4578S")
        err_text = self.page.get_validation_error()
        self.assertIn("S followed by 4 digits", err_text)


if __name__ == "__main__":
    unittest.main(verbosity=2)
    