from selenium.webdriver.common.by import By
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.support.ui import WebDriverWait


class StudentPage:
    def __init__(self, driver, timeout=5):
        self.driver = driver
        self.wait = WebDriverWait(driver, timeout)

    def open(self, url):
        self.driver.get(url)
        self.wait.until(EC.presence_of_element_located((By.ID, "students-table")))

    def add_student(self, name, roll_no):
        name_el = self.wait.until(EC.element_to_be_clickable((By.ID, "name-input")))
        name_el.clear()
        name_el.send_keys(name)

        roll_el = self.driver.find_element(By.ID, "roll-input")
        roll_el.clear()
        roll_el.send_keys(str(roll_no))

        self.driver.find_element(By.ID, "add-btn").click()

    def student_row_text(self, roll_no):
        row = self.wait.until(EC.presence_of_element_located((By.ID, f"row-{roll_no}")))
        return row.text

    def get_average_display(self, roll_no):
        avg_cell = self.wait.until(EC.presence_of_element_located((By.ID, f"avg-{roll_no}")))
        return avg_cell.text.strip()

    def get_validation_error(self):
        err_el = self.wait.until(EC.visibility_of_element_located((By.ID, "validation-error")))
        return err_el.text.strip()

    def add_score(self, roll_no, score):
        score_input = self.wait.until(EC.element_to_be_clickable((By.ID, f"score-input-{roll_no}")))
        score_input.clear()
        score_input.send_keys(str(score))
        self.driver.find_element(By.ID, f"score-btn-{roll_no}").click()

    def wait_for_average(self, roll_no, expected_text):
        self.wait.until(
            EC.text_to_be_present_in_element((By.ID, f"avg-{roll_no}"), str(expected_text))
        )
        return self.get_average_display(roll_no)